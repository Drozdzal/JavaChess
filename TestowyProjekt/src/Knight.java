import javax.swing.*;
import java.awt.*;

public class Knight extends Piece{
    public Knight(boolean isWhite)
    {
        this.setWhite(isWhite);
        if (isWhite) {
            this.setFile_path("bialy_skoczek.png");
        }
        else{
            this.setFile_path("czarny_skoczek.png");
        }

        setPieceVisualization(new ImageIcon(new ImageIcon(this.getFile_path()).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        this.setIcon(getPieceVisualization());
    }

    @Override
    public void getPossibleMoves() {
        char row,column;
        int goForward=1;
        getAllMoves().clear();
        String possible_position;

        column= getActual_position().charAt(0);
        row= getActual_position().charAt(1);



        for(int i=-1;i<2;i+=2) {
            goForward=i;
            possible_position = "" + (char) ((int) (column) - 1*goForward) + (char) (row - 2 * goForward);
            if (!isSquareOccupiedByAlly(possible_position))
            {
                getAllMoves().add(possible_position);
            }
            possible_position = "" + (char) ((int) (column) - 2*goForward) + (char) (row - 1 * goForward);

            if (!isSquareOccupiedByAlly(possible_position))
            {
                getAllMoves().add(possible_position);
            }
            possible_position = "" + (char) ((int) (column) - 1*goForward) + (char) (row + 2 * goForward);

            if (!isSquareOccupiedByAlly(possible_position))
            {
                getAllMoves().add(possible_position);
            }
            possible_position = "" + (char) ((int) (column) - 2*goForward) + (char) (row + 1 * goForward);

            if (!isSquareOccupiedByAlly(possible_position))
            {
                getAllMoves().add(possible_position);
            }

        }
    }
}
