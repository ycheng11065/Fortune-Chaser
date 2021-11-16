package model;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Treasure {
    public static final int SIZEX = 20;
    public static final int SIZEY = 20;
    public static final Color COLOUR = new Color(255, 255, 51);

    private int xcoord;
    private int ycoord;
    private String msg;

    public Treasure(int x, int y) {
        xcoord = x;
        ycoord = y;
    }

    //EFFECT: Return true if bullet hit enemy
    public boolean hit(Player p) {
        Rectangle treasureRectangle = new Rectangle(getX() - SIZEX / 2, getY() - SIZEY / 2,
                SIZEX, SIZEY);
        Rectangle playerRectangle = new Rectangle(p.getXcoord() - Player.SIZEX / 2,
                p.getYcoord() - Player.SIZEY / 2, Player.SIZEX, Player.SIZEY);
        return treasureRectangle.intersects(playerRectangle);

    }

    public int getX() {
        return xcoord;
    }

    public int getY() {
        return ycoord;
    }

    public void addMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }


}
