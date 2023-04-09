import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }


    public void sendMessage(String message) {
        out.println(message);
    }
    public String waitForMessage() {
        try {
//            while (true) {
                String message = in.readLine();
//                if (message == null) {
//                    break;
//                }
                if (message.equals("YOU WON"))
                {
                    int choice = JOptionPane.showConfirmDialog(null, "You've WON. Do you want to quit?", "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (choice == JOptionPane.OK_OPTION) {
                        System.exit(0); // exit the application
                    }
                }
                System.out.println("RECEIVED MESSAGE FROM OPPONENT"+message);
                return message;
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Problem";
    }
}
