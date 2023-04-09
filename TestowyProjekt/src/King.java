import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class King extends Piece{
    public King(boolean isWhite)
    {
        this.isWhite=isWhite;
        if (isWhite) {
            this.file_path = "bialy_krol.png";
        }
        else{
            this.file_path = "czarny_krol.png";
        }

        pieceVisualization= new ImageIcon(new ImageIcon(this.file_path).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        this.setIcon(pieceVisualization);
    }

    @Override
    public void getPossibleMoves() {
        String possible_position="A2";
        char column,row;
        column=actual_position.charAt(0);
        row=actual_position.charAt(1);
        for (int i = -1; i <= 1; i += 1) {
            for (int j = -1; j <= 1; j += 1) {
                possible_position = "" + (char) ((int) (column) - i) + (char) (row - j);
                if (!isSquareOccupiedByAlly(possible_position)) {
                    allMoves.add(possible_position);
                }
            }
        }

    }
}
