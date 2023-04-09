import java.io.IOException;
import java.io.SyncFailedException;
import javax.swing.JOptionPane;
public abstract class  GameMode {

    public Player activePlayer;


    protected Client client;
    protected boolean isWhite = true;

    Chessboard board;
    boolean isGameStarter;

    Piece pieceToRemove = null;
    String currentChange;
    boolean waitingForOpponent=false;

    String desiredSquare;
    String previousSquare;
    public void gameStart()
    {
        if (this instanceof MultiplayerMode) {
            try {
                System.out.println("Stworzono klienta");
                client = new Client("localhost", 5555);


            } catch (IOException error) {
                System.out.println(error);
                // code to handle the exception
            }
            if (!activePlayer.isWhite) {
                System.out.println("Zaczynam jako czarny wiec czekam");
                switchTurn();
            }
        }
    }

    public void setPlayer(String name, boolean isWhite)
    {
        activePlayer = new Player(name,isWhite);
    }
    public void setChessboard(Chessboard board)
    {
        this.board=board;
    }


    public int getRow(int X) {
        // To 30 jest przez paseczek
        int row = 1 + (X - 30 - board.boardRowOffset) / board.sizeOfSquare;
        return row;
    }

    public char getColumn(int Y) {
        int asciAdd = 1 + (Y - board.boardColumnOffset) / board.sizeOfSquare;
        return (char) (64 + asciAdd);
    }

    Piece canPickPiece(int X, int Y) {
        String primary_square;
        primary_square = "" + getColumn(X) + getRow(Y);
        Piece picked_piece;
        //gdy pole które nacisnelismy okupuje figura
        if (board.chessboard.get(primary_square).occupied) {
            // jesli figure chce podniesc gracz do ktorego figura nalezy
            if (activePlayer.isWhite == board.chessboard.get(primary_square).piece.isWhite) {
                //jesli nie ma mata

                picked_piece = board.chessboard.get(primary_square).piece;
                return picked_piece;

            } else{
                System.out.println("Its not your turn to pick piece or you picked opponent piece");
            }

        }

        return null;
    }

    boolean isMate()
    {
//        Piece kingPiece = new King(false);
        Piece kingPiece = null;
        for (Piece piece: Piece.all_pieces) {
            if ((piece instanceof King) && (activePlayer.isWhite==piece.isWhite)) {
                kingPiece = piece;
                System.out.println("Znaleziono krola na");
                System.out.println(kingPiece.actual_position);
            }
        }
        String kingPosition = kingPiece.actual_position;
        for(Piece piece: Piece.all_pieces)
        {
            if (piece.isWhite!=kingPiece.isWhite)
            {
                piece.getPossibleMoves();
                if (piece.movePossible(kingPiece.actual_position)){
                    return true;
                }

            }
        }
        return false;
    }
    boolean executeMove(Piece piece, int X,int Y){


        previousSquare=piece.actual_position;
        desiredSquare= "" + getColumn(X) + getRow(Y);


        piece.getPossibleMoves();
        System.out.println("All moves possible " +piece.allMoves);
        System.out.println("Desired Squar e"+desiredSquare);
        if (piece.movePossible(desiredSquare) && piece.isWhite==activePlayer.isWhite)
        {
            piece.firstMove=false;
            if (board.chessboard.get(desiredSquare).occupied)
            {
                boolean moveResult= attackPiece(piece,desiredSquare);

                return moveResult;
            }
            else
            {
                boolean moveResult= movePiece(piece,desiredSquare);

                return moveResult;
            }
        }
        else{
            return false;
        }

    }

    void opponentMove(Piece piece, String to){

        piece.getPossibleMoves();
        System.out.println("RECEIVED piece WHICH WAS ON"+piece.actual_position);
        System.out.println("Figura moze sie poruszac do:");
        String primarySquare = piece.actual_position;
        piece.actual_position=to;





        if (board.chessboard.get(to).occupied)
            {
                piece.setLocation(board.chessboard.get(to).getX(),board.chessboard.get(to).getY());

                pieceToRemove = board.chessboard.get(to).piece;
                board.chessboard.get(primarySquare).piece = null;
                board.chessboard.get(primarySquare).setOccupied(false);
                board.chessboard.get(to).piece = piece;
                board.chessboard.get(to).setOccupied(true);

            }
            else
            {
                piece.setLocation(board.chessboard.get(to).getX(),board.chessboard.get(to).getY());
                board.chessboard.get(primarySquare).piece=null;
                board.chessboard.get(primarySquare).setOccupied(false);
                board.chessboard.get(to).piece = piece;
                board.chessboard.get(to).setOccupied(true);


            }
            System.out.println("CHESSBOARD AT SECOND POSSITION");
            System.out.println(board.chessboard.get(to).occupied);


        }
//    }
    boolean attackPiece(Piece piece, String to)
    {
        String primarySquare = piece.actual_position;
        piece.actual_position=to;

        if (!isMate())
        {
            piece.setLocation(board.chessboard.get(to).getX(),board.chessboard.get(to).getY());

            pieceToRemove = board.chessboard.get(to).piece;
//            Piece.all_pieces.remove(pieceToRemove);
            board.chessboard.get(primarySquare).piece = null;
            board.chessboard.get(primarySquare).setOccupied(false);
            board.chessboard.get(to).piece = piece;
            board.chessboard.get(to).setOccupied(true);
            return true;
        }
        else{
            int choice = JOptionPane.showOptionDialog(null, "There is mate do you want to surrender?",
                    "Options", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"Don't surrender", "Surrender"}, null);

            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("Dont surrender");
            } else if (choice == JOptionPane.NO_OPTION) {
                System.out.println("Surrender");
                int second_choice = JOptionPane.showConfirmDialog(null, "You've lost. Do you want to quit?", "Game Over",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                client.sendMessage("YOU WON");
                if (choice == JOptionPane.OK_OPTION) {
                    System.exit(0); // exit the application
                }

            }
        }
        piece.actual_position=primarySquare;
        return false;
    }

    boolean movePiece(Piece piece,String to)
    {
        String primarySquare = piece.actual_position;
        piece.actual_position=to;
        if (!isMate())
        {
            piece.setLocation(board.chessboard.get(to).getX(),board.chessboard.get(to).getY());
        board.chessboard.get(primarySquare).piece=null;
        board.chessboard.get(primarySquare).setOccupied(false);
        board.chessboard.get(to).piece = piece;
        board.chessboard.get(to).setOccupied(true);
            return true;
        }
        else{
            int choice = JOptionPane.showOptionDialog(null, "There is mate do you want to surrender?",
                    "Options", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"Don't surrender", "Surrender"}, null);

            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("Dont surrender");
            } else if (choice == JOptionPane.NO_OPTION) {
                System.out.println("Surrender");
                int second_choice = JOptionPane.showConfirmDialog(null, "You've lost. Do you want to quit?", "Game Over",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                client.sendMessage("YOU WON");
                if (choice == JOptionPane.OK_OPTION) {
                    System.exit(0); // exit the application
                }

            }
        }
        piece.actual_position=primarySquare;
        return false;
    }

    public abstract void switchTurn();

}

