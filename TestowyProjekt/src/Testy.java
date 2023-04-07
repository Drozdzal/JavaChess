import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Testy extends JFrame implements ActionListener {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public Testy() {
        super("Menu App");

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the file menu
        JMenu fileMenu = new JMenu("File");

        // Create the "New" menu item
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(this);
        fileMenu.add(newMenuItem);

        // Create the "Open" menu item
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(this);
        fileMenu.add(openMenuItem);

        // Add the file menu to the menu bar
        menuBar.add(fileMenu);

        // Create the panels
        JPanel newPanel = new JPanel();
        newPanel.setBackground(Color.BLUE);
        JPanel openPanel = new JPanel();
        openPanel.setBackground(Color.RED);

        // Create the card panel and set its layout to CardLayout
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Add the panels to the card panel with unique names
        cardPanel.add(newPanel, "new");
        cardPanel.add(openPanel, "open");

        // Add the menu bar and card panel to the frame
        add(menuBar, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        // Set the size and visibility of the frame
        setSize(400, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the name of the button that was clicked
        String buttonName = e.getActionCommand();

        // Show the corresponding panel based on the button name
        cardLayout.show(cardPanel, buttonName.toLowerCase());
    }


}
