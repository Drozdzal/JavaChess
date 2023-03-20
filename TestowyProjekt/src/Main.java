import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Main{

    public static void main(String args[])
    {
//        Map chessboard = new HashMap();
//        boolean isWhite= false;
//        JFrame rozgrywka=new JFrame();
//        rozgrywka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        rozgrywka.setSize(700, 700);
//        rozgrywka.setLayout(null);
//        Square kwadrat;
//        String square_id;
//        for(int row=1;row<=8;row+=1) {
//            for (int column = 1; column <= 8; column += 1) {
//                kwadrat = new Square(isWhite, 75*row,75*column);
//                rozgrywka.add(kwadrat);
//                isWhite=!(isWhite);
//                square_id=""+(char)(64+row)+column;
//                System.out.println(square_id);
//                chessboard.put(square_id,kwadrat);
//            }
//            isWhite=!(isWhite);
//        }
//        System.out.println(chessboard.get("A1"));
//        Piece szach = new Piece();
//        rozgrywka.add(szach);
//        rozgrywka.setVisible(true);
        Game rozgrywka= new Game();



    }



}

