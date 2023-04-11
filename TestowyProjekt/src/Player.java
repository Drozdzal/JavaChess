public class Player {
    private String name;
    private boolean isWhite;
    Player(String name,boolean isWhite)
    {
        this.setName(name);
        this.setWhite(isWhite);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }
}