import java.io.IOException;
import javax.swing.JOptionPane;
public abstract class  GameMode {

    public Player activePlayer;


    protected Client client;
    protected boolean isWhite = true;

    private Chessboard board;
    private boolean isGameStarter;

    private Piece pieceToRemove = null;
    private String currentChange;
    private boolean waitingForOpponent=false;

    private String desiredSquare;
    private String previousSquare;
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
            if (!activePlayer.isWhite()) {
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
        this.setBoard(board);
    }


    public int getRow(int X) {
        // To 30 jest przez paseczek
        int row = 1 + (X - 30 - getBoard().boardRowOffset) / getBoard().sizeOfSquare;
        return row;
    }

    public char getColumn(int Y) {
        int asciAdd = 1 + (Y - getBoard().boardColumnOffset) / getBoard().sizeOfSquare;
        return (char) (64 + asciAdd);
    }

    Piece canPickPiece(int X, int Y) {
        String primary_square;
        primary_square = "" + getColumn(X) + getRow(Y);
        Piece picked_piece;
        //gdy pole które nacisnelismy okupuje figura
        if (getBoard().chessboard.get(primary_square).isOccupied()) {
            // jesli figure chce podniesc gracz do ktorego figura nalezy
            if (activePlayer.isWhite() == getBoard().chessboard.get(primary_square).piece.isWhite()) {
                //jesli nie ma mata

                picked_piece = getBoard().chessboard.get(primary_square).piece;
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
            if ((piece instanceof King) && (activePlayer.isWhite() == piece.isWhite())) {
                kingPiece = piece;
                System.out.println("Znaleziono krola na");
                System.out.println(kingPiece.getActual_position());
            }
        }
        String kingPosition = kingPiece.getActual_position();
        for(Piece piece: Piece.all_pieces)
        {
            if (piece.isWhite() != kingPiece.isWhite())
            {
                piece.getPossibleMoves();
                if (piece.movePossible(kingPiece.getActual_position())){
                    return true;
                }

            }
        }
        return false;
    }
    boolean executeMove(Piece piece, int X,int Y){


        setPreviousSquare(piece.getActual_position());
        setDesiredSquare("" + getColumn(X) + getRow(Y));


        piece.getPossibleMoves();
        System.out.println("All moves possible " + piece.getAllMoves());
        System.out.println("Desired Squar e"+ getDesiredSquare());
        if (piece.movePossible(getDesiredSquare()) && piece.isWhite() == activePlayer.isWhite())
        {
            piece.setFirstMove(false);
            if (getBoard().chessboard.get(getDesiredSquare()).isOccupied())
            {
                boolean moveResult= attackPiece(piece, getDesiredSquare());

                return moveResult;
            }
            else
            {
                boolean moveResult= movePiece(piece, getDesiredSquare());

                return moveResult;
            }
        }
        else{
            return false;
        }

    }

    void opponentMove(Piece piece, String to){

        piece.getPossibleMoves();
        System.out.println("RECEIVED piece WHICH WAS ON"+ piece.getActual_position());
        System.out.println("Figura moze sie poruszac do:");
        String primarySquare = piece.getActual_position();
        piece.setActual_position(to);





        if (getBoard().chessboard.get(to).isOccupied())
            {
                piece.setLocation(getBoard().chessboard.get(to).getX(), getBoard().chessboard.get(to).getY());

                setPieceToRemove(getBoard().chessboard.get(to).piece);
                getBoard().chessboard.get(primarySquare).piece = null;
                getBoard().chessboard.get(primarySquare).setOccupied(false);
                getBoard().chessboard.get(to).piece = piece;
                getBoard().chessboard.get(to).setOccupied(true);

            }
            else
            {
                piece.setLocation(getBoard().chessboard.get(to).getX(), getBoard().chessboard.get(to).getY());
                getBoard().chessboard.get(primarySquare).piece=null;
                getBoard().chessboard.get(primarySquare).setOccupied(false);
                getBoard().chessboard.get(to).piece = piece;
                getBoard().chessboard.get(to).setOccupied(true);


            }
            System.out.println("CHESSBOARD AT SECOND POSSITION");
            System.out.println(getBoard().chessboard.get(to).isOccupied());


        }
//    }
    boolean attackPiece(Piece piece, String to)
    {
        String primarySquare = piece.getActual_position();
        piece.setActual_position(to);

        if (!isMate())
        {
            piece.setLocation(getBoard().chessboard.get(to).getX(), getBoard().chessboard.get(to).getY());

            setPieceToRemove(getBoard().chessboard.get(to).piece);
//            Piece.all_pieces.remove(pieceToRemove);
            getBoard().chessboard.get(primarySquare).piece = null;
            getBoard().chessboard.get(primarySquare).setOccupied(false);
            getBoard().chessboard.get(to).piece = piece;
            getBoard().chessboard.get(to).setOccupied(true);
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
        piece.setActual_position(primarySquare);
        return false;
    }

    boolean movePiece(Piece piece,String to)
    {
        String primarySquare = piece.getActual_position();
        piece.setActual_position(to);
        if (!isMate())
        {
            piece.setLocation(getBoard().chessboard.get(to).getX(), getBoard().chessboard.get(to).getY());
        getBoard().chessboard.get(primarySquare).piece=null;
        getBoard().chessboard.get(primarySquare).setOccupied(false);
        getBoard().chessboard.get(to).piece = piece;
        getBoard().chessboard.get(to).setOccupied(true);
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
        piece.setActual_position(primarySquare);
        return false;
    }

    public abstract void switchTurn();

    public Chessboard getBoard() {
        return board;
    }

    public void setBoard(Chessboard board) {
        this.board = board;
    }

    public boolean isGameStarter() {
        return isGameStarter;
    }

    public void setGameStarter(boolean gameStarter) {
        isGameStarter = gameStarter;
    }

    public Piece getPieceToRemove() {
        return pieceToRemove;
    }

    public void setPieceToRemove(Piece pieceToRemove) {
        this.pieceToRemove = pieceToRemove;
    }

    public String getCurrentChange() {
        return currentChange;
    }

    public void setCurrentChange(String currentChange) {
        this.currentChange = currentChange;
    }

    public boolean isWaitingForOpponent() {
        return waitingForOpponent;
    }

    public void setWaitingForOpponent(boolean waitingForOpponent) {
        this.waitingForOpponent = waitingForOpponent;
    }

    public String getDesiredSquare() {
        return desiredSquare;
    }

    public void setDesiredSquare(String desiredSquare) {
        this.desiredSquare = desiredSquare;
    }

    public String getPreviousSquare() {
        return previousSquare;
    }

    public void setPreviousSquare(String previousSquare) {
        this.previousSquare = previousSquare;
    }
}

