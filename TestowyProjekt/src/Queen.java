import javax.swing.*;
import java.awt.*;

public class Queen extends Piece{
    public Queen(boolean isWhite)
    {
        this.setWhite(isWhite);
        if (isWhite) {
            this.setFile_path("bialy_hetman.png");
        }
        else{
            this.setFile_path("czarny_hetman.png");
        }

        setPieceVisualization(new ImageIcon(new ImageIcon(this.getFile_path()).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        this.setIcon(getPieceVisualization());
    }
    @Override
    public void getPossibleMoves(){
        char column,row;
        column= getActual_position().charAt(0);
        row= getActual_position().charAt(1);
        String possible_position;
        getAllMoves().clear();
        for(int i=1; i<=7;i++)
        {
            possible_position = "" + (char)((int)(column)) + (char)(row-1*i);;


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
            possible_position = "" + (char)((int)(column)-1*i) + (char)(row);;


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
            possible_position = "" + (char)((int)(column)+1*i) + (char)(row);;

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
            possible_position = "" + (char)((int)(column)) + (char)(row+1*i);;


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
