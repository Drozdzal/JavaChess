import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
public class Game extends JFrame{
//    JFrame okno_rozgrywki = new JFrame();;
    HashMap<String,Square> chessboard = new HashMap<String,Square>();
    boolean isWhite= false;
    int sizeOfBoard=600;
    int sizeOfSquare=75;

    int boardColumnOffset = 75;
    int boardRowOffset =75;
    Game() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 700);
        this.setLayout(null);
        Mouse mycha = new Mouse(this);
        this.addMouseListener(mycha);
        this.addMouseMotionListener(mycha);
        printChessBoard();
        printPieces();
        this.setVisible(true);
    }

    void printChessBoard()
    {
        Square square;
        String square_id;
        for(int row=0;row<8;row+=1) {
            for (int column = 0; column < 8; column += 1) {
                square = new Square(isWhite, boardColumnOffset+sizeOfSquare*column, boardRowOffset+sizeOfSquare*row);
                this.add(square);
                square_id=""+(char)(65+column)+(row+1);
                chessboard.put(square_id,square);
                isWhite=!(isWhite);
//
            }
            isWhite=!(isWhite);
        }
        System.out.println(chessboard);
    }

    void printPieces()
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
        setPieceOnSquare("A8",czarny_rook);

        Rook bialy_rook = new Rook(true);
        setPieceOnSquare("H1",bialy_rook);
        bialy_rook = new Rook(true);
        setPieceOnSquare("H8",bialy_rook);

        Bishop czarny_Bishop = new Bishop(false);
        setPieceOnSquare("C1",czarny_Bishop);
        czarny_Bishop = new Bishop(false);
        setPieceOnSquare("C8",czarny_Bishop);

        Bishop bialy_Bishop = new Bishop(true);
        setPieceOnSquare("F1",bialy_Bishop);
        bialy_Bishop = new Bishop(false);
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
        czarny_King = new King(false);
        setPieceOnSquare("D8",czarny_King);

        Queen czarny_Queen = new Queen(false);
        setPieceOnSquare("E1",czarny_Queen);
        czarny_Queen = new Queen(false);
        setPieceOnSquare("E8",czarny_Queen);



    }

    void setPieceOnSquare(String position, Piece piece)
    {
        int x,y;
        System.out.println("Trying to set new piece");
        x=chessboard.get(position).X;
        y=chessboard.get(position).Y;
        System.out.println(position + x + y);

        System.out.println(chessboard.get(position));
        piece.setOnBoard(x,y);
        piece.actual_position=position;
        this.add(piece,0);
        chessboard.get(position).piece=piece;
        chessboard.get(position).occupied=true;
    }
}
/*
PieceDragAndDropListener pieceDragAndDropListener = new PieceDragAndDropListener(this);
 */