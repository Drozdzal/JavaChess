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
//        ArrayList<String> possible_moves = new ArrayList<String>();
//        return possible_moves;
        //
    }
}
