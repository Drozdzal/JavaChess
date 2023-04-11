import javax.swing.*;
import java.awt.*;

public class King extends Piece{
    public King(boolean isWhite)
    {
        this.setWhite(isWhite);
        if (isWhite) {
            this.setFile_path("bialy_krol.png");
        }
        else{
            this.setFile_path("czarny_krol.png");
        }

        setPieceVisualization(new ImageIcon(new ImageIcon(this.getFile_path()).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        this.setIcon(getPieceVisualization());
    }

    @Override
    public void getPossibleMoves() {
        String possible_position="A2";
        char column,row;
        column= getActual_position().charAt(0);
        row= getActual_position().charAt(1);
        for (int i = -1; i <= 1; i += 1) {
            for (int j = -1; j <= 1; j += 1) {
                possible_position = "" + (char) ((int) (column) - i) + (char) (row - j);
                if (!isSquareOccupiedByAlly(possible_position)) {
                    getAllMoves().add(possible_position);
                }
            }
        }

    }
}
