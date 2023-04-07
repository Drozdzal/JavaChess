import java.io.IOException;
import java.io.SyncFailedException;

public class GameMode {

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
        if (board.chessboard.get(primary_square).occupied) {
            if (activePlayer.isWhite == board.chessboard.get(primary_square).piece.isWhite) {
                picked_piece = board.chessboard.get(primary_square).piece;
                return picked_piece;
            }

        }

        return null;
    }


    boolean executeMove(Piece piece, int X,int Y){

        String desiredSquare;
        desiredSquare= "" + getColumn(X) + getRow(Y);

        piece.getPossibleMoves();
        System.out.println(piece.allMoves);
        System.out.println(desiredSquare);
        if (piece.movePossible(desiredSquare))
        {
            client.sendMessage(piece.actual_position+""+desiredSquare);

            if (board.chessboard.get(desiredSquare).occupied)
            {
                attackPiece(piece,desiredSquare);
                switchTurn();
                return true;
            }
        else
            {
                movePiece(piece,desiredSquare);
                switchTurn();
                return true;
            }
        }
        else{
            return false;
        }

    }

    void opponentMove(Piece piece, String to){
        
        piece.getPossibleMoves();
        System.out.println(piece.allMoves);
        System.out.println(to);
        
        if (piece.movePossible(to))
        {

            if (board.chessboard.get(to).occupied)
            {
                attackPiece(piece,to);
            }
            else
            {
                movePiece(piece,to);
            }
        }
    }
    void attackPiece(Piece piece, String to)
    {
        String primarySquare = piece.actual_position;
        piece.setLocation(board.chessboard.get(to).getX(),board.chessboard.get(to).getY());
        piece.actual_position=to;
        pieceToRemove=board.chessboard.get(to).piece;
        board.chessboard.get(to).piece = null;
        Piece.all_pieces.remove(board.chessboard.get(to).piece);
        board.chessboard.get(primarySquare).piece=null;
        board.chessboard.get(primarySquare).setOccupied(false);
        board.chessboard.get(to).piece = piece;
        board.chessboard.get(to).setOccupied(true);
    }

    void movePiece(Piece piece,String to)
    {
        String primarySquare = piece.actual_position;
        piece.setLocation(board.chessboard.get(to).getX(),board.chessboard.get(to).getY());
        piece.actual_position=to;
        board.chessboard.get(primarySquare).piece=null;
        board.chessboard.get(primarySquare).setOccupied(false);
        board.chessboard.get(to).piece = piece;
        board.chessboard.get(to).setOccupied(true);



    }
    void switchTurn()
    {
        System.out.println("Waiting for oponent move");
        String message=client.waitForMessage();
        String from="" + message.charAt(0) + message.charAt(1);
        System.out.println("Picking piece from"+from);
        String to="" + message.charAt(2) + message.charAt(3);
        System.out.println("Picking piece to"+to);
        Piece choosen_piece=Chessboard.chessboard.get(from).piece;
        opponentMove(choosen_piece, to);
        System.out.println("Move executed");
    }
}

