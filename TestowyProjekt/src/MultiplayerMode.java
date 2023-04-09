public class MultiplayerMode extends GameMode{
    @Override
    Piece canPickPiece(int X, int Y) {
        String primary_square;
        primary_square = "" + getColumn(X) + getRow(Y);
        Piece picked_piece;
        //gdy pole które nacisnelismy okupuje figura
        System.out.println("Picking piece in Multiplayer");
        if (!waitingForOpponent) {
            if (board.chessboard.get(primary_square).occupied) {
                // jesli figure chce podniesc gracz do ktorego figura nalezy
                if (activePlayer.isWhite == board.chessboard.get(primary_square).piece.isWhite) {
                    //jesli nie ma mata

                    picked_piece = board.chessboard.get(primary_square).piece;
                    return picked_piece;

                } else {
                    System.out.println("Its not your turn to pick piece or you picked opponent piece");
                }

            }
        }

        return null;
    }
    @Override
    public void switchTurn()
    {
        waitingForOpponent=true;

        System.out.println("Waiting for oponent move");
        String message=client.waitForMessage();
        String from="" + message.charAt(0) + message.charAt(1);
        System.out.println("Picking piece from"+from);
        String to="" + message.charAt(2) + message.charAt(3);
        System.out.println("Picking piece to"+to);
        Piece choosen_piece=Chessboard.chessboard.get(from).piece;
        opponentMove(choosen_piece, to);
        System.out.println("RECEIVED OPPONEND MESSAGE" + message);
        waitingForOpponent=false;


    }
}
