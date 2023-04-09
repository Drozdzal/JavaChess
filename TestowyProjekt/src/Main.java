
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

