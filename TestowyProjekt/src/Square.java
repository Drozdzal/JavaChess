import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//public class Square extends JLabel implements MouseListener{
public class Square extends JLabel{
    int X;
    int Y;
    int width = 75;
    int height = 75;
    boolean occupied = false;
    Piece piece;


    Square(boolean isWhite, int xp, int yp) {
        this.X=xp;
        this.Y=yp;

        this.setBounds(X, Y, width, height);
        if (isWhite) {
            this.setBackground(Color.GRAY);
        }
        else{
            this.setBackground(Color.white);
        }
        this.setOpaque(true);
//        this.addMouseListener(this);
        this.setVisible(true);
    }





}