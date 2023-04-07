public class GameMode {
    protected Player player1;
    protected Player player2;
    protected Player activePlayer;
    protected boolean isWhite = true;

    Chessboard board;
    boolean isGameStarter;

    Piece pieceToRemove = null;
    String currentChange;

//    public abstract void switchTurn();

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
            if (this.isWhite == board.chessboard.get(primary_square).piece.isWhite) {
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
        System.out.println("Move Possible in execute");
        System.out.println(piece.allMoves);
        System.out.println(desiredSquare);
        if (piece.movePossible(desiredSquare))
        {
            if (board.chessboard.get(desiredSquare).occupied)
            {
                attackPiece(piece,X,Y);
//                piece.getPossibleMoves();
                return true;
            }
        else
            {
                movePiece(piece,X,Y);
//                piece->getPossibleMoves();
                return true;
            }
        }
        else{
            return false;
        }

    }
    void attackPiece(Piece piece, int X,int Y)
    {
        String primarySquare = piece.actual_position;
        String secondarySquare = "" + getColumn(X) + getRow(Y);
        piece.setLocation(board.chessboard.get(secondarySquare).getX(),board.chessboard.get(secondarySquare).getY());
        piece.actual_position=secondarySquare;
        pieceToRemove=board.chessboard.get(secondarySquare).piece;
        board.chessboard.get(secondarySquare).piece = null;
        Piece.all_pieces.remove(board.chessboard.get(secondarySquare).piece);
        board.chessboard.get(primarySquare).piece=null;
        board.chessboard.get(primarySquare).setOccupied(false);
        board.chessboard.get(secondarySquare).piece = piece;
        board.chessboard.get(secondarySquare).setOccupied(true);
    }

    void movePiece(Piece piece,int X,int Y)
    {
        String primarySquare = piece.actual_position;
        String secondarySquare = "" + getColumn(X) + getRow(Y);
        piece.setLocation(board.chessboard.get(secondarySquare).getX(),board.chessboard.get(secondarySquare).getY());
        piece.actual_position=secondarySquare;
        board.chessboard.get(primarySquare).piece=null;
        board.chessboard.get(primarySquare).setOccupied(false);
        board.chessboard.get(secondarySquare).piece = piece;
        board.chessboard.get(secondarySquare).setOccupied(true);



    }
}

