import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece{
    int goForward=1; // for white +1 for black multiplayer will be -1
    public Pawn(boolean isWhite)
    {
        this.setWhite(isWhite);
        if (isWhite) {
            this.setFile_path("bialy_pionek.png");
        }
        else{
            this.setFile_path("czarny_pionek.png");
        }

        setPieceVisualization(new ImageIcon(new ImageIcon(this.getFile_path()).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        this.setIcon(getPieceVisualization());
    }
    @Override
    public void getPossibleMoves() {
        char row,column;
        String possible_position;
        getAllMoves().clear();

        System.out.println("Getting possible pawn moves");
        column= getActual_position().charAt(0);
        row= getActual_position().charAt(1);

            if (isWhite()) {
                goForward=1;
            }
            else {
            goForward=-1;
            }


        possible_position=""+column+(char)((int)row-1*goForward);
        if (!isSquareOccupied(possible_position)) {
            getAllMoves().add(possible_position);
        }

        possible_position = "" + column + (char) ((int) row - 2*goForward);


        if (isFirstMove() && (!isSquareOccupied(possible_position))) {

            getAllMoves().add(possible_position);
        }


        possible_position=""+(char)((int)(column)-1) + (char)(row-1*goForward);
        if (isSquareOccupied(possible_position))
        {
            getAllMoves().add(possible_position);
        }
        else{
            System.out.println("possition not occupied"+possible_position);
        }

        possible_position=""+(char)((int)(column)+1) + (char)(row-1*goForward);
        if (isSquareOccupied(possible_position))
        {
            getAllMoves().add(possible_position);
        }
        else{
            System.out.println("possition not occupied"+possible_position);
        }
        System.out.println("Possible moves");
        System.out.println(getAllMoves());
    }
}
