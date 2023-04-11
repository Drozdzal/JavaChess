import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;







public class View extends JFrame{
    private JButton joinMultiplayerButton;
    private JButton multiplayerServerButton;
    private JButton exitButton;
    private JButton singleplayerButton;


    public Singleplayer singleplayer = new Singleplayer();
    JPanel mainPanel;
    public View() {



        mainPanel = new JPanel(new GridLayout(4, 1));
        setSingleplayerButton(new JButton("Singleplayer Game"));

        setMultiplayerServerButton(new JButton("Create Multilpayer Game"));
        setJoinMultiplayerButton(new JButton("Join Multiplayer Game"));

        setExitButton(new JButton("Exit"));

        mainPanel.add(getSingleplayerButton());
        mainPanel.add(getMultiplayerServerButton());
        mainPanel.add(getJoinMultiplayerButton());
        mainPanel.add(getExitButton());


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

    public JButton getJoinMultiplayerButton() {
        return joinMultiplayerButton;
    }

    public void setJoinMultiplayerButton(JButton joinMultiplayerButton) {
        this.joinMultiplayerButton = joinMultiplayerButton;
    }

    public JButton getMultiplayerServerButton() {
        return multiplayerServerButton;
    }

    public void setMultiplayerServerButton(JButton multiplayerServerButton) {
        this.multiplayerServerButton = multiplayerServerButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JButton getSingleplayerButton() {
        return singleplayerButton;
    }

    public void setSingleplayerButton(JButton singleplayerButton) {
        this.singleplayerButton = singleplayerButton;
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
            Square square = Chessboard.chessboard.get(singlePiece.getActual_position());
            singlePiece.setBounds(square.getX(), square.getY(),75,75);

            this.add(singlePiece,0);
        }
    }

    public void resetPieces()
    {
        this.removeAll();
        this.displayChessboard(Chessboard.chessboard);
        for (int i = 0; i < Piece.all_pieces.size(); i++) {
            Piece singlePiece = Piece.all_pieces.get(i);
            Square square = Chessboard.chessboard.get(singlePiece.getActual_position());
            singlePiece.setBounds(square.getX(), square.getY(),75,75);

            this.add(singlePiece,0);
        }
    }

    public void displayMainMenu()
    {

    }

}
