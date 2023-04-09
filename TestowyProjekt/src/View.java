import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;







public class View extends JFrame{
    public JButton multiplayerButton;
    public JButton singleplayerButton;
    public JButton exitButton;


    public Singleplayer singleplayer = new Singleplayer();
    JPanel mainPanel;
    public View() {



        mainPanel = new JPanel(new GridLayout(3, 1));
        singleplayerButton = new JButton("Create Multilpayer Game");
        multiplayerButton = new JButton("Join Multiplayer Game");
        exitButton = new JButton("Join Multiplayer Game");

//        exitButton = new JButton("Quit");
        mainPanel.add(singleplayerButton);
        mainPanel.add(multiplayerButton);
        mainPanel.add(exitButton);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0); // add some padding top and bottom
        centerPanel.add(mainPanel, gbc);

        // Add the centerPanel to the content pane
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        // Set the size of the window and make it visible
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);

        setVisible(true);


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
            square.setBounds(square.getX(),square.getY(),75,75);
            this.add(square,0);
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

    public void resetPieces()
    {
        for (int i = 0; i < Piece.all_pieces.size(); i++) {
            Piece singlePiece = Piece.all_pieces.get(i);
            this.remove(singlePiece);
        }
        for (int i = 0; i < Piece.all_pieces.size(); i++) {
            Piece singlePiece = Piece.all_pieces.get(i);
            Square square = Chessboard.chessboard.get(singlePiece.actual_position);
            singlePiece.setBounds(square.X,square.Y,75,75);

            this.add(singlePiece,0);
        }
    }

    public void displayMainMenu()
    {

    }

}
