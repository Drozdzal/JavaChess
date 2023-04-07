import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    public void getPossibleMoves() {
        char row,column;
        String possible_position;
        allMoves.clear();

        column=actual_position.charAt(0);
        row=actual_position.charAt(1);

        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)-1*i) + (char)(row-1*i);;

            if (isSquareOccupied(possible_position))
            {
                if (!isSquareOccupiedByAlly(possible_position))
                {
                    allMoves.add(possible_position);
                }
                break;
            }
            allMoves.add(possible_position);
        }

        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)+1*i) + (char)(row-1*i);;
            

            if (isSquareOccupied(possible_position))
            {
                if (!isSquareOccupiedByAlly(possible_position))
                {
                    allMoves.add(possible_position);
                }
                break;
            }
            allMoves.add(possible_position);

        }


        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)-1*i) + (char)(row+1*i);;


            if (isSquareOccupied(possible_position))
            {
                if (!isSquareOccupiedByAlly(possible_position))
                {
                    allMoves.add(possible_position);
                }
                break;
            }
            allMoves.add(possible_position);
        }


        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)+1*i) + (char)(row+1*i);;
            
            if (isSquareOccupied(possible_position))
            {
                if (!isSquareOccupiedByAlly(possible_position))
                {
                    allMoves.add(possible_position);
                }
                break;
            }
            allMoves.add(possible_position);
        }

    }
}
