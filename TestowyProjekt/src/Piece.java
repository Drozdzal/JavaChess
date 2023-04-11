import javax.swing.*;

import java.util.ArrayList;

public abstract class Piece extends JLabel{

    private boolean isWhite;
    private boolean firstMove=true;
    private String file_path;
    private ImageIcon pieceVisualization;
    private String actual_position;
    private ArrayList<String> allMoves = new ArrayList<String>();
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
        if (getAllMoves().size() < 1) {
            getPossibleMoves();
        }
        if (getAllMoves().contains(desiredSquare)) {
            return true;
        } else {

            return false;
        }
    }
    public boolean isSquareOccupied(String possible_position)
    {
        int pieces_through = 0;
        while (Piece.all_pieces.size() > pieces_through) {
            if(possible_position.equals(all_pieces.get(pieces_through).getActual_position()))
            {
                return true;
            }

            pieces_through++ ;
        }
        return false;

    }

    public boolean isSquareOccupiedByAlly(String possible_position)
    {
        int pieces_through = 0;
        while (Piece.all_pieces.size() > pieces_through) {
            if(possible_position.equals(all_pieces.get(pieces_through).getActual_position()))
            {
                if(all_pieces.get(pieces_through).isWhite() == this.isWhite()) {
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


    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public ImageIcon getPieceVisualization() {
        return pieceVisualization;
    }

    public void setPieceVisualization(ImageIcon pieceVisualization) {
        this.pieceVisualization = pieceVisualization;
    }

    public String getActual_position() {
        return actual_position;
    }

    public void setActual_position(String actual_position) {
        this.actual_position = actual_position;
    }

    public ArrayList<String> getAllMoves() {
        return allMoves;
    }

    public void setAllMoves(ArrayList<String> allMoves) {
        this.allMoves = allMoves;
    }
}
