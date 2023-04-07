import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChessController implements MouseListener,MouseMotionListener,ActionListener{
    private Model model;
    private View view;
    private Chessboard board;
    private Piece picked_piece;
    private GameMode gameMode;
    private boolean isWhite=true;

//    private Mouse mouse;

    public ChessController(Model model, View view) {
        board = new Chessboard();
        gameMode = new GameMode();
        board.createBoard();
        board.createPieces();
        gameMode.setChessboard(board);
        this.model = model;
        this.view = view;
        view.singleplayerButton.addActionListener(this);
        view.multiplayerButton.addActionListener(this);
//        view.exitButton.addActionListener(this);
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
        view.displayMainMenu();

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("ACctionPerformed");

        if (e.getSource() == view.singleplayerButton) {
//            view.layout.show(view.cards,"Singleplayer");
            System.out.println("nacisnieto singleplayer");
            view.mainPanel.setVisible(false);
            view.setLayout(null);
            view.setPreferredSize(new Dimension(1000,1000));
            view.singleplayer.setVisible(true);
            view.displayChessboard(board.chessboard);
            view.displayPieces(Piece.all_pieces);
            view.repaint();

        } else if (e.getSource() == view.multiplayerButton) {
//            CardLayout cardLayout = (CardLayout) view.cards.getLayout();
//            cardLayout.show(view.cards, "Multiplayer");
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (picked_piece==null){

            picked_piece=gameMode.canPickPiece(e.getX(),e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (picked_piece!=null)
        {
            if (gameMode.executeMove(picked_piece,e.getX(),e.getY()))
            {
                System.out.println("Executed move");
                if (gameMode.pieceToRemove!=null)
                {
                    view.remove(gameMode.pieceToRemove);;
                    gameMode.pieceToRemove=null;
                }
            }
            else
            {
                backToStartingPosition();
                System.out.println("Backed to begginig");

            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if ((picked_piece!=null)) {
            picked_piece.setLocation(e.getX(),e.getY());

        }
        else
        {
            System.out.println("nic nie ma");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // nothing
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
        picked_piece.setLocation(board.chessboard.get(lastPosition).X,board.chessboard.get(lastPosition).Y);
        picked_piece=null;
    }
    }

