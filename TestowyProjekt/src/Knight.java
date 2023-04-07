import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(boolean isWhite)
    {
        this.isWhite=isWhite;
        if (isWhite) {
            this.file_path = "bialy_skoczek.png";
        }
        else{
            this.file_path = "czarny_skoczek.png";
        }

        pieceVisualization= new ImageIcon(new ImageIcon(this.file_path).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        this.setIcon(pieceVisualization);
    }

    @Override
    public void getPossibleMoves() {
        char row,column;
        int goForward=1;
        allMoves.clear();
        String possible_position;

        column=actual_position.charAt(0);
        row=actual_position.charAt(1);



        for(int i=-1;i<2;i+=2) {
            goForward=i;
            possible_position = "" + (char) ((int) (column) - 1*goForward) + (char) (row - 2 * goForward);
            if (!isSquareOccupiedByAlly(possible_position))
            {
                allMoves.add(possible_position);
            }
            possible_position = "" + (char) ((int) (column) - 2*goForward) + (char) (row - 1 * goForward);

            if (!isSquareOccupiedByAlly(possible_position))
            {
                allMoves.add(possible_position);
            }
            possible_position = "" + (char) ((int) (column) - 1*goForward) + (char) (row + 2 * goForward);

            if (!isSquareOccupiedByAlly(possible_position))
            {
                allMoves.add(possible_position);
            }
            possible_position = "" + (char) ((int) (column) - 2*goForward) + (char) (row + 1 * goForward);

            if (!isSquareOccupiedByAlly(possible_position))
            {
                allMoves.add(possible_position);
            }

        }
    }
}
