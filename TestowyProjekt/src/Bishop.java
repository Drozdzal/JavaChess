import javax.swing.*;
import java.awt.*;

public class Bishop extends Piece{
    public Bishop(boolean isWhite)
    {
        this.isWhite=isWhite;
        if (isWhite) {
            this.file_path = "bialy_goniec.png";
        }
        else{
            this.file_path = "czarny_goniec.png";
        }

        pieceVisualization= new ImageIcon(new ImageIcon(this.file_path).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        this.setIcon(pieceVisualization);
    }

    @Override
    public void movesPossible() {
        //
    }
}
