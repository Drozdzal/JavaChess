public class SingleplayerMode extends GameMode {

    @Override
    public void switchTurn()
    {
         activePlayer.isWhite=!activePlayer.isWhite;
         System.out.println("Changed active player");
         System.out.println("Now Active player" + activePlayer.isWhite);


    }
}
