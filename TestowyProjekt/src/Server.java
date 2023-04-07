import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        try {
            while (true) {
                Socket clientSocket1 = serverSocket.accept();
                Socket clientSocket2 = serverSocket.accept();
                System.out.println("Connected to clients " + clientSocket1.getInetAddress() + " and " + clientSocket2.getInetAddress());

                new ClientHandler(clientSocket1, clientSocket2).start();
                new ClientHandler(clientSocket2, clientSocket1).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
