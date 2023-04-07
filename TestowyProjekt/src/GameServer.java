import java.net.*;
import java.io.*;

public class GameServer {
    private ServerSocket serverSocket;
    private Socket[] clients;
    private ObjectInputStream[] inputStreams;
    private ObjectOutputStream[] outputStreams;
    private int numClients;

    public GameServer(int port, int numClients) {
        try {
            this.numClients = numClients;
            serverSocket = new ServerSocket(port);
            clients = new Socket[numClients];
            inputStreams = new ObjectInputStream[numClients];
            outputStreams = new ObjectOutputStream[numClients];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            for (int i = 0; i < numClients; i++) {
                System.out.println("Waiting for client " + (i + 1) + "...");
                clients[i] = serverSocket.accept();
                System.out.println("Client " + (i + 1) + " connected.");
                inputStreams[i] = new ObjectInputStream(clients[i].getInputStream());
                outputStreams[i] = new ObjectOutputStream(clients[i].getOutputStream());
                new Thread(new ClientHandler(i)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Object data) {
        for (int i = 0; i < numClients; i++) {
            try {
                outputStreams[i].writeObject(data);
                outputStreams[i].flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler implements Runnable {
        private int clientIndex;

        public ClientHandler(int clientIndex) {
            this.clientIndex = clientIndex;
        }

        public void run() {
            try {
                while (true) {
                    Object data = inputStreams[clientIndex].readObject();
                    broadcast(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clients[clientIndex].close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
