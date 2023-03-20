import javax.swing.*;
import java.awt.*;

public class Rook extends Piece{
    public Rook(boolean isWhite)
    {
        this.isWhite=isWhite;
        if (isWhite) {
            this.file_path = "bialy_wieza.png";
        }
        else{
            this.file_path = "czarny_wieza.png";
        }

        pieceVisualization= new ImageIcon(new ImageIcon(this.file_path).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        this.setIcon(pieceVisualization);
    }
    @Override
    public void movesPossible() {
        //
    }
}
