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
        view.getMultiplayerServerButton().addActionListener(this);
        view.getJoinMultiplayerButton().addActionListener(this);
        view.getSingleplayerButton().addActionListener(this);
        view.getExitButton().addActionListener(this);

        view.addMouseListener(this);
        view.addMouseMotionListener(this);
        view.displayMainMenu();

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("ACctionPerformed");

        if (e.getSource() == view.getMultiplayerServerButton()) {
            System.out.println("nacisnieto singleplayer");
            view.mainPanel.setVisible(false);
            view.setLayout(null);
            view.setPreferredSize(new Dimension(1000,1000));
            view.singleplayer.setVisible(true);
            view.displayChessboard(model.getBoard().chessboard);
            view.displayPieces(Piece.all_pieces);
            view.repaint();
            model.setGameMode(new MultiplayerMode());
            model.getGameMode().setChessboard(model.getBoard());
            model.getGameMode().setPlayer("Michal",true);

            try {
            server = new Server(5555);
            Thread serverThread = new Thread(server);
            serverThread.start();


            } catch (IOException error) {
                System.out.println(error);
                // code to handle the exception
            }
            model.getGameMode().gameStart();



        } else if (e.getSource() == view.getJoinMultiplayerButton()) {
            view.mainPanel.setVisible(false);
            view.setLayout(null);
            view.setPreferredSize(new Dimension(1000,1000));
            view.singleplayer.setVisible(true);
            view.displayChessboard(model.getBoard().chessboard);
            view.displayPieces(Piece.all_pieces);
            view.repaint();
            model.setGameMode(new MultiplayerMode());
            model.getGameMode().setChessboard(model.getBoard());
            model.getGameMode().setPlayer("Ewa",false);
            model.getGameMode().gameStart();


        }
        else if (e.getSource() == view.getSingleplayerButton()) {
            System.out.println("Odpalono singla");
            view.mainPanel.setVisible(false);
            view.setLayout(null);
            view.setPreferredSize(new Dimension(1000,1000));
            view.singleplayer.setVisible(true);
            view.displayChessboard(model.getBoard().chessboard);
            view.displayPieces(Piece.all_pieces);
            view.repaint();
            model.setGameMode(new SingleplayerMode());
            model.getGameMode().setChessboard(model.getBoard());
            model.getGameMode().setPlayer("Ewa",false);
            model.getGameMode().gameStart();


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

            picked_piece= model.getGameMode().canPickPiece(e.getX(),e.getY());
        }
        else{
            picked_piece=null;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (picked_piece!=null)
        {
            if (model.getGameMode().executeMove(picked_piece,e.getX(),e.getY()))
            {
                System.out.println("Executed move");

                if (model.getGameMode() instanceof MultiplayerMode) {
                    System.out.println("Multilpayer Game sending message to opponent");
                    model.getGameMode().client.sendMessage(model.getGameMode().getPreviousSquare() +""+ model.getGameMode().getDesiredSquare());
                }
                else {
                    System.out.println("HA");
                }
                if (model.getGameMode().getPieceToRemove() !=null)
                {

                    view.remove(model.getGameMode().getPieceToRemove());
                    Piece.all_pieces.remove(model.getGameMode().getPieceToRemove());
                    model.getGameMode().setPieceToRemove(null);
                    picked_piece=null;
                }
                model.getGameMode().switchTurn();

                if (model.getGameMode().getPieceToRemove() !=null)
                {

                    view.remove(model.getGameMode().getPieceToRemove());
                    Piece.all_pieces.remove(model.getGameMode().getPieceToRemove());
                    model.getGameMode().setPieceToRemove(null);
                    picked_piece=null;
                }
            }
            else
            {
                if (model.getGameMode().getPieceToRemove() !=null) {
                    view.remove(model.getGameMode().getPieceToRemove());
                    Piece.all_pieces.remove(model.getGameMode().getPieceToRemove());
                    model.getGameMode().setPieceToRemove(null);
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
        if (model.getGameMode().getPieceToRemove() == null) {
            if (model.getGameMode().getPieceToRemove() !=null) {
                view.remove(model.getGameMode().getPieceToRemove());
                Piece.all_pieces.remove(model.getGameMode().getPieceToRemove());
                model.getGameMode().setPieceToRemove(null);
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
        String lastPosition= picked_piece.getActual_position();
        picked_piece.setLocation(model.getBoard().chessboard.get(lastPosition).getX(), model.getBoard().chessboard.get(lastPosition).getY());
        picked_piece=null;
    }
    }

