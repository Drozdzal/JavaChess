import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece{
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
    public void movesPossible() {
        //
    }
}
