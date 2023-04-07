import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;







public class View extends JFrame{
    public JButton multiplayerButton;
    public JButton singleplayerButton;

    public Singleplayer singleplayer = new Singleplayer();
    JPanel mainPanel;
    public View() {



        mainPanel = new JPanel(new GridLayout(4, 1));
        singleplayerButton = new JButton("Singleplayer");
        multiplayerButton = new JButton("Multiplayer");
//        exitButton = new JButton("Quit");
        mainPanel.add(singleplayerButton);
        mainPanel.add(multiplayerButton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(new GridLayout());
        this.add(singleplayer);
        this.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setVisible(true);

        // Set the initial view to the "Main" view





//        this.setVisible(true);

    }



    private class Multiplayer extends JPanel {
        public Multiplayer() {
            add(new JLabel("Multiplayer"));
            // Add components to View 1
        }
    }




    public void displayChessboard(HashMap<String, Square> chessboard) {
        for (Map.Entry<String, Square> singleSquare : chessboard.entrySet()) {

            Square square = singleSquare.getValue();
            square.setLocation(square.X,square.Y);
            this.add(square);
        }
    }

    public void displayPieces(ArrayList<Piece> all_pieces)
    {
        for (int i = 0; i < all_pieces.size(); i++) {
            Piece singlePiece = all_pieces.get(i);
            Square square = Chessboard.chessboard.get(singlePiece.actual_position);
            singlePiece.setBounds(square.X,square.Y,75,75);

            this.add(singlePiece,0);
        }
    }

    public void displayMainMenu()
    {

    }

}
