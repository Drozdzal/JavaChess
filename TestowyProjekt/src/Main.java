
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



//import java.io.IOException;

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
