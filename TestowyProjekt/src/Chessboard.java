import java.util.HashMap;

public class Chessboard {
        int sizeOfBoard=600;
        int sizeOfSquare=75;

        int boardColumnOffset = 75;
        int boardRowOffset =75;
        public static HashMap<String,Square> chessboard = new HashMap<String,Square>();
    void createBoard()
    {


        Square square;
        String square_id;
        boolean isWhite=false;
        for(int row=0;row<8;row+=1) {
            for (int column = 0; column < 8; column += 1) {
                square = new Square(isWhite, boardColumnOffset+sizeOfSquare*column, boardRowOffset+sizeOfSquare*row);
                square_id=""+(char)(65+column)+(row+1);
                chessboard.put(square_id,square);
                isWhite=!(isWhite);
//
            }
            isWhite=!(isWhite);
        }
    }


    void setPieceOnSquare(String position, Piece piece)
    {
        int x,y;
        x=chessboard.get(position).X;
        y=chessboard.get(position).Y;

        piece.setOnBoard(x,y);
        piece.actual_position=position;
        chessboard.get(position).piece=piece;
        chessboard.get(position).occupied=true;
    }


    void createPieces()
    {

        for(int i=1;i<=8;i++)
        {
            Pawn czarny_pionek = new Pawn(false);
            System.out.println(""+(char)(64+i)+2);
            setPieceOnSquare(""+(char)(64+i)+2,czarny_pionek);
            Pawn bialy_pionek = new Pawn(true);
            setPieceOnSquare(""+(char)(64+i)+7,bialy_pionek);

        }
        Rook czarny_rook = new Rook(false);
        setPieceOnSquare("A1",czarny_rook);
        czarny_rook = new Rook(false);
        setPieceOnSquare("H1",czarny_rook);

        Rook bialy_rook = new Rook(true);
        setPieceOnSquare("A8",bialy_rook);
        bialy_rook = new Rook(true);
        setPieceOnSquare("H8",bialy_rook);

        Bishop czarny_Bishop = new Bishop(false);
        setPieceOnSquare("C1",czarny_Bishop);
        czarny_Bishop = new Bishop(false);
        setPieceOnSquare("F1",czarny_Bishop);

        Bishop bialy_Bishop = new Bishop(true);
        setPieceOnSquare("C8",bialy_Bishop);
        bialy_Bishop = new Bishop(true);
        setPieceOnSquare("F8",bialy_Bishop);

        Knight czarny_Knight = new Knight(false);
        setPieceOnSquare("B1",czarny_Knight);
        czarny_Knight = new Knight(false);
        setPieceOnSquare("G1",czarny_Knight);

        Knight bialy_Knight = new Knight(true);
        setPieceOnSquare("B8",bialy_Knight);
        bialy_Knight = new Knight(true);
        setPieceOnSquare("G8",bialy_Knight);

        King czarny_King = new King(false);
        setPieceOnSquare("D1",czarny_King);
        King bialy_King  = new King(true);
        setPieceOnSquare("D8",bialy_King);

        Queen czarny_Queen = new Queen(false);
        setPieceOnSquare("E1",czarny_Queen);
        Queen bialy_Queen = new Queen(true);
        setPieceOnSquare("E8",bialy_Queen);



    }
//
//    void setPieceOnSquare(String position, Piece piece)
//    {
//        int x,y;
//        System.out.println("Trying to set new piece");
//        x=chessboard.get(position).X;
//        y=chessboard.get(position).Y;
//        System.out.println(position + x + y);
//
//        System.out.println(chessboard.get(position));
//        piece.setOnBoard(x,y);
//        piece.actual_position=position;
//        this.add(piece,0);
//        chessboard.get(position).piece=piece;
//        chessboard.get(position).occupied=true;
//    }
//    public void changeTurn()
//    {
//        this.isWhite=!this.isWhite;
//    }
}

//class Board
//{
//
//    public:
//    void createPieces();
//    void createBoard();
//    void setPieceOnSquare(Piece* piece, std::string sqaurePosition);
////private:
//    std::map<std::string,Square*> board;
//    int sizeOfSquare=75;
//    int boardColumnOffset = 75;
//    int boardRowOffset =75;
//
//
//};
