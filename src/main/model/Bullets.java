package model;

import java.awt.*;

public class Bullets {

    public static final int SIZEX = 6;
    public static final int SIZEY = 6;
    public static final int XRATE = 2;
    public static final int YRATE = 2;
    public static final Color COLOR = new Color(0,0,0);


    private int xcoord;
    private int ycoord;
    private String dir;

    //EFFECT: Construct a bullet
    public Bullets(int x, int y, String dir) {
        this.xcoord = x;
        this.ycoord = y;

    }

    // MODIFY: this
    //EFFECT: Move the bullet in the direction it is already in
    public void move() {
        if (dir == "Down") {
            ycoord = ycoord - YRATE;
        } else if (dir == "Up") {
            ycoord = ycoord + YRATE;
        } else if (dir == "Right") {
            xcoord = xcoord + XRATE;
        } else {
            xcoord = xcoord - XRATE;
        }

    }

    //EFFECT: Get x coordinate
    public int getXcoord() {
        return xcoord;
    }

    //EFFECT: Get x coordinate
    public int getYcoord() {
        return ycoord;
    }

}
