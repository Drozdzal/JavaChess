import javax.swing.*;
import java.awt.*;

public class Bishop extends Piece{
    public Bishop(boolean isWhite)
    {
        this.setWhite(isWhite);
        if (isWhite) {
            this.setFile_path("bialy_goniec.png");
        }
        else{
            this.setFile_path("czarny_goniec.png");
        }

        setPieceVisualization(new ImageIcon(new ImageIcon(this.getFile_path()).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        this.setIcon(getPieceVisualization());
    }

    @Override
    public void getPossibleMoves() {
        char row,column;
        String possible_position;
        getAllMoves().clear();

        column= getActual_position().charAt(0);
        row= getActual_position().charAt(1);

        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)-1*i) + (char)(row-1*i);;

            if (isSquareOccupied(possible_position))
            {
                if (!isSquareOccupiedByAlly(possible_position))
                {
                    getAllMoves().add(possible_position);
                }
                break;
            }
            getAllMoves().add(possible_position);
        }

        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)+1*i) + (char)(row-1*i);;
            

            if (isSquareOccupied(possible_position))
            {
                if (!isSquareOccupiedByAlly(possible_position))
                {
                    getAllMoves().add(possible_position);
                }
                break;
            }
            getAllMoves().add(possible_position);

        }


        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)-1*i) + (char)(row+1*i);;


            if (isSquareOccupied(possible_position))
            {
                if (!isSquareOccupiedByAlly(possible_position))
                {
                    getAllMoves().add(possible_position);
                }
                break;
            }
            getAllMoves().add(possible_position);
        }


        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)+1*i) + (char)(row+1*i);;
            
            if (isSquareOccupied(possible_position))
            {
                if (!isSquareOccupiedByAlly(possible_position))
                {
                    getAllMoves().add(possible_position);
                }
                break;
            }
            getAllMoves().add(possible_position);
        }

    }
}
