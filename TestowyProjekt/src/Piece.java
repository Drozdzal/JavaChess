import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//public abstract Piece extends JLabel implements MouseListener{
//    ImageIcon simple;
//
//    Piece() {
//        this.setOpaque(true);
//        this.addMouseListener(this);
//        this.setVisible(true);
//
//        simple= new ImageIcon("bialy_skoczek.png");
//        this.setIcon(simple);
//        this.setBounds(0, 0, 200, 200);
//        this.addMouseListener(this);
//
//    }
//
//
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Piece extends JLabel{

    boolean isWhite;
    boolean firstMove=true;
    String file_path;
    ImageIcon pieceVisualization;
    String actual_position;
//
    Piece() {
        this.setOpaque(false);
    }
    void setOnBoard(int x, int y)
    {
        this.setBounds(x,y,75,75);
//        this.setVisible(true);
    }
    public abstract void movesPossible();



}
