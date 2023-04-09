import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece{
    int goForward=1; // for white +1 for black multiplayer will be -1
    public Pawn(boolean isWhite)
    {
        this.isWhite=isWhite;
        if (isWhite) {
            this.file_path = "bialy_pionek.png";
        }
        else{
            this.file_path = "czarny_pionek.png";
        }

        pieceVisualization= new ImageIcon(new ImageIcon(this.file_path).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        this.setIcon(pieceVisualization);
    }
    @Override
    public void getPossibleMoves() {
        char row,column;
        String possible_position;
        allMoves.clear();

        System.out.println("Getting possible pawn moves");
        column=actual_position.charAt(0);
        row=actual_position.charAt(1);

            if (isWhite) {
                goForward=1;
            }
            else {
            goForward=-1;
            }


        possible_position=""+column+(char)((int)row-1*goForward);
        if (!isSquareOccupied(possible_position)) {
            allMoves.add(possible_position);
        }

        possible_position = "" + column + (char) ((int) row - 2*goForward);


        if (firstMove && (!isSquareOccupied(possible_position))) {

            allMoves.add(possible_position);
        }


        possible_position=""+(char)((int)(column)-1) + (char)(row-1*goForward);
        if (isSquareOccupied(possible_position))
        {
            allMoves.add(possible_position);
        }
        else{
            System.out.println("possition not occupied"+possible_position);
        }

        possible_position=""+(char)((int)(column)+1) + (char)(row-1*goForward);
        if (isSquareOccupied(possible_position))
        {
            allMoves.add(possible_position);
        }
        else{
            System.out.println("possition not occupied"+possible_position);
        }
        System.out.println("Possible moves");
        System.out.println(allMoves);
    }
}
