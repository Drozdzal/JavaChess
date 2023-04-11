public class Model
{
    private GameMode gameMode;
    private Chessboard board;

    private Player myself;
    public Model()
    {
        System.out.println("Konstruktor Modelu");
//        gameMode = new GameMode();
        setBoard(new Chessboard());
        getBoard().createBoard();
        getBoard().createPieces();
    }
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public Chessboard getBoard() {
        return board;
    }

    public void setBoard(Chessboard board) {
        this.board = board;
    }

    public Player getMyself() {
        return myself;
    }

    public void setMyself(Player myself) {
        this.myself = myself;
    }
}