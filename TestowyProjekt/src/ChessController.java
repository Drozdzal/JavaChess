import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;


public class ChessController implements MouseListener,MouseMotionListener,ActionListener{
    private Model model;
    private View view;
    private Server server;
//    private Chessboard board;
    private Piece picked_piece;
    private boolean isWhite=true;


//    private Mouse mouse;

    public ChessController(Model model, View view) {

        this.model = model;
        this.view = view;
        view.multiplayerServerButton.addActionListener(this);
        view.joinMultiplayerButton.addActionListener(this);
        view.singleplayerButton.addActionListener(this);
        view.exitButton.addActionListener(this);

        view.addMouseListener(this);
        view.addMouseMotionListener(this);
        view.displayMainMenu();

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("ACctionPerformed");

        if (e.getSource() == view.multiplayerServerButton) {
            System.out.println("nacisnieto singleplayer");
            view.mainPanel.setVisible(false);
            view.setLayout(null);
            view.setPreferredSize(new Dimension(1000,1000));
            view.singleplayer.setVisible(true);
            view.displayChessboard(model.board.chessboard);
            view.displayPieces(Piece.all_pieces);
            view.repaint();
            model.gameMode=new MultiplayerMode();
            model.gameMode.setChessboard(model.board);
            model.gameMode.setPlayer("Michal",true);

            try {
            server = new Server(5555);
            Thread serverThread = new Thread(server);
            serverThread.start();


            } catch (IOException error) {
                System.out.println(error);
                // code to handle the exception
            }
            model.gameMode.gameStart();



        } else if (e.getSource() == view.joinMultiplayerButton) {
            view.mainPanel.setVisible(false);
            view.setLayout(null);
            view.setPreferredSize(new Dimension(1000,1000));
            view.singleplayer.setVisible(true);
            view.displayChessboard(model.board.chessboard);
            view.displayPieces(Piece.all_pieces);
            view.repaint();
            model.gameMode=new MultiplayerMode();
            model.gameMode.setChessboard(model.board);
            model.gameMode.setPlayer("Ewa",false);
            model.gameMode.gameStart();


        }
        else if (e.getSource() == view.singleplayerButton) {
            System.out.println("Odpalono singla");
            view.mainPanel.setVisible(false);
            view.setLayout(null);
            view.setPreferredSize(new Dimension(1000,1000));
            view.singleplayer.setVisible(true);
            view.displayChessboard(model.board.chessboard);
            view.displayPieces(Piece.all_pieces);
            view.repaint();
            model.gameMode= new SingleplayerMode();
            model.gameMode.setChessboard(model.board);
            model.gameMode.setPlayer("Ewa",false);
            model.gameMode.gameStart();


        }


    }
    @Override
    public void mousePressed(MouseEvent e) {
//        if (model.gameMode.pieceToRemove!=null)
//        {
////            view.remove(model.gameMode.pieceToRemove);
////            Piece.all_pieces.remove(model.gameMode.pieceToRemove);
////            model.gameMode.pieceToRemove=null;
////            picked_piece=null;
//            view.resetPieces();
//
//        }
        if (picked_piece==null){

            picked_piece=model.gameMode.canPickPiece(e.getX(),e.getY());
        }
        else{
            picked_piece=null;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (picked_piece!=null)
        {
            if (model.gameMode.executeMove(picked_piece,e.getX(),e.getY()))
            {
                System.out.println("Executed move");

                if (model.gameMode instanceof MultiplayerMode) {
                    System.out.println("Multilpayer Game sending message to opponent");
                    model.gameMode.client.sendMessage(model.gameMode.previousSquare+""+model.gameMode.desiredSquare);
                }
                else {
                    System.out.println("HA");
                }
                if (model.gameMode.pieceToRemove!=null)
                {

                    view.remove(model.gameMode.pieceToRemove);
                    Piece.all_pieces.remove(model.gameMode.pieceToRemove);
                    model.gameMode.pieceToRemove=null;
                    picked_piece=null;
                }
                model.gameMode.switchTurn();

                if (model.gameMode.pieceToRemove!=null)
                {

                    view.remove(model.gameMode.pieceToRemove);
                    Piece.all_pieces.remove(model.gameMode.pieceToRemove);
                    model.gameMode.pieceToRemove=null;
                    picked_piece=null;
                }
            }
            else
            {
                if (model.gameMode.pieceToRemove!=null) {
                    view.remove(model.gameMode.pieceToRemove);
                    Piece.all_pieces.remove(model.gameMode.pieceToRemove);
                    model.gameMode.pieceToRemove=null;
                }
                backToStartingPosition();
                System.out.println("Backed to begginig");

            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if ((picked_piece != null)) {
            picked_piece.setLocation(e.getX(), e.getY());


        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (model.gameMode.pieceToRemove == null) {
            if (model.gameMode.pieceToRemove!=null) {
                view.remove(model.gameMode.pieceToRemove);
                Piece.all_pieces.remove(model.gameMode.pieceToRemove);
                model.gameMode.pieceToRemove=null;
            }


        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }


    public void backToStartingPosition()
    {
        String lastPosition=picked_piece.actual_position;
        picked_piece.setLocation(model.board.chessboard.get(lastPosition).X,model.board.chessboard.get(lastPosition).Y);
        picked_piece=null;
    }
    }

