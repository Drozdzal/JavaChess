//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class Button {
//    private static JPanel cards;
//
//    public static void main(String[] args) {
//        // create a new JFrame
//        JFrame frame = new JFrame("Next Page Example");
//
//        // create a panel for the first page
//        JPanel firstPage = new JPanel();
//        JLabel firstLabel = new JLabel("Welcome to the first page!");
//        firstPage.add(firstLabel);
//
//        // create a panel for the second page
//        JPanel secondPage = new JPanel();
//        JLabel secondLabel = new JLabel("Welcome to the second page!");
//        secondPage.add(secondLabel);
//
//        // create a panel for the card layout
//        cards = new JPanel(new CardLayout());
//        cards.add(firstPage, "First Page");
//        cards.add(secondPage, "Second Page");
//
//        // create a button to go to the next page
//        JButton nextButton = new JButton("Next");
//        nextButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                CardLayout cl = (CardLayout)(cards.getLayout());
//                cl.show(cards, "Second Page");
//            }
//        });
//
//        // add the button to the first page
//        firstPage.add(nextButton);
//
//        // add the card layout to the frame
//        frame.add(cards);
//
//        // set the size of the frame
//        frame.setSize(300, 200);
//
//        // make the frame visible
//        frame.setVisible(true);
//    }
//}