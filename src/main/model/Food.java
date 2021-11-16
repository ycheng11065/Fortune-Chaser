package model;

import java.awt.*;

public class Food {
    public static final int SIZEX = 20;
    public static final int SIZEY = 20;
    public static final Color COLOUR = new Color(0, 255, 0);

    private int xcoord;
    private int ycoord;

    public Food(int x, int y) {
        xcoord = x;
        ycoord = y;
    }

   //EFFECT: Return true if bullet hit enemy
    public boolean hit(Player p) {
        Rectangle foodRectangle = new Rectangle(getX() - SIZEX / 2, getY() - SIZEY / 2,
                SIZEX, SIZEY);
        Rectangle playerRectangle = new Rectangle(p.getXcoord() - Player.SIZEX / 2,
                p.getYcoord() - Player.SIZEY / 2, Player.SIZEX, Player.SIZEY);
        return foodRectangle.intersects(playerRectangle);

    }

    public int getX() {
        return xcoord;
    }

    public int getY() {
        return ycoord;
    }

}
