//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//public class Mouse implements MouseListener, MouseMotionListener{
//
//    ChessController controller;
//    int sizeOfSquare;
//    String primary_square,secondary_square;
//    Piece picked_piece;
//    boolean choosen;
//    public Mouse(ChessController controller){
//        this.controller=controller;
//        sizeOfSquare=controller.board.sizeOfSquare;
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        primary_square=""+getColumn(e.getX())+getRow(e.getY());
//        if (controller.chessboard.get(primary_square).occupied)  {
//            if (controller.isWhite == controller.chessboard.get(primary_square).piece.isWhite) {
//                picked_piece = controller.chessboard.get(primary_square).piece;
//            }
//
//        }
//
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        secondary_square=""+getColumn(e.getX())+getRow(e.getY());
//        System.out.println(picked_piece.movesPossible());
//        if ((picked_piece!=null)&&(picked_piece.movesPossible().contains(secondary_square)))
//        {
//            if (controller.chessboard.get(secondary_square).occupied)
//            {
//                if (picked_piece.isWhite!=controller.chessboard.get(secondary_square).piece.isWhite) {
//                    attackPiece();
//                    controller.changeTurn();
//                }
//                else {
//                    backToStartingPosition();
//                }
//            }
//            else {
//                setNewSquare();
//                controller.changeTurn();
//
//            }
//        }
//        else if(picked_piece!=null)
//        {
//            backToStartingPosition();
//        }
//
//
//
//
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        if ((picked_piece!=null) && (controller.chessboard.get(primary_square).occupied)) {
//            controller.chessboard.get(primary_square).piece.setLocation(e.getX(),e.getY());
//
//        }
//        else
//        {
//            System.out.println("nic nie ma");
//        }
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        // nothing
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//        // nothing
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//        // nothing
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//
//
//    }
//
//    public int getRow(int X)
//    {
//        // To 30 jest przez paseczek
//        int row= 1+(X-controller.boardRowOffset)/sizeOfSquare;
//        return row;
//    }
//
//    public char getColumn(int Y)
//    {
//        int asciAdd=1+(Y-30-controller.boardColumnOffset)/sizeOfSquare;
//        return (char)(64+asciAdd);
//    }
//
//    public void setNewSquare()
//    {
//        picked_piece.firstMove=false;
//        picked_piece.setLocation(controller.chessboard.get(secondary_square).X,controller.chessboard.get(secondary_square).Y);
//        picked_piece.actual_position=secondary_square;
//        controller.chessboard.get(secondary_square).occupied=true;
//        controller.chessboard.get(secondary_square).piece=picked_piece;
//        controller.chessboard.get(primary_square).piece=null;
//        controller.chessboard.get(primary_square).occupied=false;
//        picked_piece=null;
//    }
//    public void backToStartingPosition()
//    {
//        picked_piece.setLocation(controller.chessboard.get(primary_square).X,controller.chessboard.get(primary_square).Y);
//        picked_piece=null;
//    }
//
//    public void attackPiece()
//    {
//        controller.remove(controller.chessboard.get(secondary_square).piece);
//        controller.chessboard.get(secondary_square).piece.actual_position=null;
//        controller.chessboard.get(secondary_square).piece=null;
//        picked_piece.setLocation(controller.chessboard.get(secondary_square).X,controller.chessboard.get(secondary_square).Y);
//        picked_piece.actual_position=secondary_square;
//        controller.chessboard.get(secondary_square).occupied=true;
//        controller.chessboard.get(secondary_square).piece=picked_piece;
//        controller.chessboard.get(primary_square).piece=null;
//        controller.chessboard.get(primary_square).occupied=false;
//        picked_piece=null;
//    }
//}
//
//
