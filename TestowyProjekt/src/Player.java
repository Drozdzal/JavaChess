//public class Player {
//    public String name;
//    public boolean isWhite;
//    Player(String name,boolean isWhite)
//    {
//        this.name=name;
//        this.isWhite=isWhite;
//    }
//}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player implements Runnable {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public Player(Socket socket) {
        this.socket = socket;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = input.readLine();
                if (message == null || message.equals("QUIT")) {
                    break;
                }
                // Handle message
                System.out.println("Received message: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public void receiveMessage() {
        try {
            // Create a new input stream to receive messages from the server
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read a message from the input stream
            String message = inFromServer.readLine();

            // Print the message to the console
            System.out.println("Received message: " + message);
        } catch (IOException e) {
            // Handle any errors that occur while receiving the message
            System.err.println("Error receiving message: " + e.getMessage());
        }
    }

}

