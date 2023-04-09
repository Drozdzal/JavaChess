import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientHandler extends Thread {
    private Socket incomingSocket;
    private Socket outgoingSocket;

    public ClientHandler(Socket incomingSocket, Socket outgoingSocket) {
        this.incomingSocket = incomingSocket;
        this.outgoingSocket = outgoingSocket;
    }
    public String waitForMessage() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(incomingSocket.getInputStream()));
        return in.readLine();
    }
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingSocket.getInputStream()));
            PrintWriter out = new PrintWriter(outgoingSocket.getOutputStream(), true);

            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }

                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
