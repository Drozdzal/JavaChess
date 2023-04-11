import javax.swing.*;
import java.awt.*;

//public class Square extends JLabel implements MouseListener{
public class Square extends JLabel{
    private int X;
    private int Y;
    private int width = 75;
    private int height = 75;
    private boolean occupied = false;
    String id;
    Piece piece;
    public void setOccupied(boolean occupied)
    {
        this.occupied=occupied;
    }

    Square(boolean isWhite, int xp, int yp) {
        this.setX(xp);
        this.setY(yp);

        this.setBounds(getX(), getY(), getWidth(), getHeight());
        this.setPreferredSize(new Dimension(getWidth(), getHeight()));
        if (isWhite) {
            this.setBackground(Color.GRAY);
        }
        else{
            this.setBackground(Color.white);
        }

        this.setOpaque(true);
        this.setVisible(true);

    }

    public void setId(String id)
    {
        this.id=id;
        this.setText(id);
        this.setHorizontalAlignment(SwingConstants.CENTER);

    }


    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }
}
