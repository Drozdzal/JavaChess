import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public class Mouse implements MouseListener, MouseMotionListener{

    Game window;
    int sizeOfSquare;
    String primary_square,secondary_square;
    Piece picked_piece;
    boolean choosen;
    public Mouse(Game window){
        this.window=window;
        sizeOfSquare=window.sizeOfSquare;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        primary_square=""+getColumn(e.getX())+getRow(e.getY());
        if (window.chessboard.get(primary_square).occupied) {
            picked_piece=window.chessboard.get(primary_square).piece;

        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        secondary_square=""+getColumn(e.getX())+getRow(e.getY());

        if ((picked_piece!=null)&&(!window.chessboard.get(secondary_square).occupied))
        {
            picked_piece.setLocation(window.chessboard.get(secondary_square).X,window.chessboard.get(secondary_square).Y);
            picked_piece.actual_position=secondary_square;
            window.chessboard.get(secondary_square).occupied=true;
            window.chessboard.get(secondary_square).piece=picked_piece;
            window.chessboard.get(primary_square).piece=null;
            window.chessboard.get(primary_square).occupied=false;
            picked_piece=null;
        }
        else if(picked_piece!=null)
        {
            System.out.println("Powrot do poczatku" +primary_square);
            picked_piece.setLocation(window.chessboard.get(primary_square).X,window.chessboard.get(primary_square).Y);
            picked_piece=null;


        }




    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if ((picked_piece!=null) && (window.chessboard.get(primary_square).occupied)) {
            window.chessboard.get(primary_square).piece.setLocation(e.getX(),e.getY());

        }
        else
        {
            System.out.println("nic nie ma");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }

    public int getRow(int X)
    {
        // To 30 jest przez paseczek
        int row= 1+(X-window.boardRowOffset)/sizeOfSquare;
        return row;
    }

    public char getColumn(int Y)
    {
        int asciAdd=1+(Y-30-window.boardColumnOffset)/sizeOfSquare;
        return (char)(64+asciAdd);
    }
}


