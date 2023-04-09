public class Model
{
    GameMode gameMode;
    Chessboard board;

    Player myself;
    public Model()
    {
        System.out.println("Konstruktor Modelu");
//        gameMode = new GameMode();
        board = new Chessboard();
        board.createBoard();
        board.createPieces();
    }
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}