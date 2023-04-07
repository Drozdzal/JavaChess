import javax.swing.*;
import java.awt.*;
import java.util.*;
//public class Main{
//
//    public static void main(String args[])
//    {
//
//        Game rozgrywka= new Game();
//
//
//
//    }
//
//
//
//}
import java.io.*;
import java.net.*;
import java.io.IOException;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        ChessController controller = new ChessController(model, view);


    }
}
//
//
//import java.net.*;
//import java.io.*;
//
//public class Main {
//    private ServerSocket serverSocket;
//    private Socket clientSocket;
//    private BufferedReader in;
//    private PrintWriter out;
//
//    public void startServer() {
//        new Thread(() -> {
//            try {
//                serverSocket = new ServerSocket(5000);
//                System.out.println("Server started. Waiting for clients to connect...");
//
//                clientSocket = serverSocket.accept();
//                System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());
//
//                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    System.out.println("Client says: " + inputLine);
//                    out.println("Server received: " + inputLine);
//                }
//
//                in.close();
//                out.close();
//                clientSocket.close();
//                serverSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    public void startClient() {
//        new Thread(() -> {
//            try {
//                Socket socket = new Socket("localhost", 5000);
//                System.out.println("Connected to server...");
//
//                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                out = new PrintWriter(socket.getOutputStream(), true);
//
//                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
//
//                String userInput;
//                while ((userInput = stdin.readLine()) != null) {
//                    out.println(userInput);
//                    System.out.println("Server says: " + in.readLine());
//                }
//
//                in.close();
//                out.close();
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    public static void main(String[] args) {
//        Main game = new Main();
//        game.startServer();
//        game.startClient();
//    }
//}


//import java.io.IOException;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        Server server = new Server(5555);
//        Thread serverThread = new Thread(server);
//        serverThread.start();
//
//        Client client1 = new Client("localhost", 5555);
//        Client client2 = new Client("localhost", 5555);
//
//        client1.start();
//        client2.start();
//
//        client1.sendMessage("hi");
//        client2.sendMessage("bye");
//    }
//}
