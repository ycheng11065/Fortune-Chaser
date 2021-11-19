package model;

import java.awt.*;

/**
 * Represents a food at position x y
 */

public class Food {
    public static final int SIZEX = 20;
    public static final int SIZEY = 20;
    public static final Color COLOUR = new Color(0, 255, 0);

    private int xcoord;
    private int ycoord;

    //EFFECTS: Create a food in the desired coordinate
    public Food(int x, int y) {
        xcoord = x;
        ycoord = y;
    }

   //EFFECTS: Return true if player hit food
    public boolean hit(Player p) {
        Rectangle foodRectangle = new Rectangle(getX() - SIZEX / 2, getY() - SIZEY / 2,
                SIZEX, SIZEY);
        Rectangle playerRectangle = new Rectangle(p.getXcoord() - Player.SIZEX / 2,
                p.getYcoord() - Player.SIZEY / 2, Player.SIZEX, Player.SIZEY);
        return foodRectangle.intersects(playerRectangle);

    }

    //EFFECTS: Return x coordinate
    public int getX() {
        return xcoord;
    }

    //EFFECTS: Return y coordinate
    public int getY() {
        return ycoord;
    }

}
