import java.io.IOException;
import java.io.SyncFailedException;

public class  GameMode {

    protected Player activePlayer;
    private boolean waitingForMessage=false;


    protected Client client;
    protected boolean isWhite = true;

    Chessboard board;
    boolean isGameStarter;

    Piece pieceToRemove = null;
    String currentChange;

    public GameMode()
    {
        System.out.println("Konstruktor Game Modelu");



    }

    public void gameStart()
    {
        try {
            System.out.println("Stworzono klienta");
            client = new Client("localhost", 5555);


        } catch (IOException error) {
            System.out.println(error);
            // code to handle the exception
        }
        if (!activePlayer.isWhite)
        {
            System.out.println("Zaczynam jako czarny wiec czekam");
            switchTurn();
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

        String desiredSquare;
        desiredSquare= "" + getColumn(X) + getRow(Y);
        String previousSquare=piece.actual_position;

        piece.getPossibleMoves();
        System.out.println(piece.allMoves);
        System.out.println(desiredSquare);
        if (piece.movePossible(desiredSquare))
        {
            piece.firstMove=false;
            if (board.chessboard.get(desiredSquare).occupied)
            {
                boolean moveResult= attackPiece(piece,desiredSquare);
                if (moveResult)
                {
//                    client.sendMessage(previousSquare+""+desiredSquare);


                    switchTurn();
                }
                return moveResult;
            }
            else
            {
                boolean moveResult= movePiece(piece,desiredSquare);
                if (moveResult)
                {
//                    client.sendMessage(previousSquare+""+desiredSquare);

                    switchTurn();
                }
                return moveResult;
            }
        }
        else{
            return false;
        }

    }

    void opponentMove(Piece piece, String to){

//        piece.getPossibleMoves();
        System.out.println("RECEIVED piece WHICH WAS ON"+piece.actual_position);
        System.out.println("Figura moze sie poruszac do:");
//        System.out.println(piece.allMoves);
//        System.out.println(to);
        String primarySquare = piece.actual_position;


        //TODO: TUTAJ SIE PRZYJRZEC BO PROBLEM Z MATEM
//        if (piece.movePossible(to))
//        {

            if (board.chessboard.get(to).occupied)
            {
                piece.setLocation(board.chessboard.get(to).getX(),board.chessboard.get(to).getY());
                pieceToRemove = board.chessboard.get(to).piece;
                Piece.all_pieces.remove(board.chessboard.get(to).piece);
                board.chessboard.get(to).piece = null;
                board.chessboard.get(primarySquare).piece=null;
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
            board.chessboard.get(to).piece = null;
            Piece.all_pieces.remove(board.chessboard.get(to).piece);
            board.chessboard.get(primarySquare).piece = null;
            board.chessboard.get(primarySquare).setOccupied(false);
            board.chessboard.get(to).piece = piece;
            board.chessboard.get(to).setOccupied(true);
            return true;
        }
        System.out.println("MATEEEEE");
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
        System.out.println("MATEEEEE");
        return false;



    }
    void switchTurn()
    {
//        System.out.println("Waiting for oponent move");
//        String message=client.waitForMessage();
//        String from="" + message.charAt(0) + message.charAt(1);
//        System.out.println("Picking piece from"+from);
//        String to="" + message.charAt(2) + message.charAt(3);
//        System.out.println("Picking piece to"+to);
//        Piece choosen_piece=Chessboard.chessboard.get(from).piece;
//        opponentMove(choosen_piece, to);
//        System.out.println("Move executed");
        activePlayer.isWhite=!activePlayer.isWhite;
    }
}

