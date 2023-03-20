import javax.swing.*;
import java.awt.*;

public class Queen extends Piece{
    public Queen(boolean isWhite)
    {
        this.isWhite=isWhite;
        if (isWhite) {
            this.file_path = "bialy_hetman.png";
        }
        else{
            this.file_path = "czarny_hetman.png";
        }

        pieceVisualization= new ImageIcon(new ImageIcon(this.file_path).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        this.setIcon(pieceVisualization);
    }
    @Override
    public void movesPossible() {
        //
    }
}
