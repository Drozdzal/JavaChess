import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


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
    ArrayList<String> allMoves = new ArrayList<String>();


    public static ArrayList<Piece> all_pieces = new ArrayList<Piece>();
//
    Piece() {
        this.setOpaque(false);
        all_pieces.add(this);
    }
    void setOnBoard(int x, int y)
    {
        this.setBounds(x,y,75,75);
        this.setVisible(true);
    }
    public abstract void getPossibleMoves();

    boolean movePossible(String desiredSquare) {
        if (allMoves.size() < 1) {
            getPossibleMoves();
        }
        if (allMoves.contains(desiredSquare)) {
            System.out.println("Ruch zawira sie w dziedzinie");
            return true;
        } else {
            System.out.println("Ruch nie zawira sie w dziedzinie");

            return false;
        }
    }
    public boolean isSquareOccupied(String possible_position)
    {
        int pieces_through = 0;
        while (this.all_pieces.size() > pieces_through) {
            if(possible_position.equals(all_pieces.get(pieces_through).actual_position))
            {
                System.out.println("Pole nie jest zajete");
                return true;
            }

            pieces_through++ ;
        }
        return false;

    }

    public boolean isSquareOccupiedByAlly(String possible_position)
    {
        int pieces_through = 0;
        while (this.all_pieces.size() > pieces_through) {
            if(possible_position.equals(all_pieces.get(pieces_through).actual_position))
            {
                if(all_pieces.get(pieces_through).isWhite==this.isWhite) {
                    System.out.println("Pole nie jest zajete przez sojusznika");
                    return true;
                }
                else {
                    return false;
                }
            }

            pieces_through++ ;
        }
        return false;

    }




}
