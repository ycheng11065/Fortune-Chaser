package model;

import java.awt.*;

// SOURCE: SpaceInvaderBase

public class Bullets {

    public static final int SIZEX = 20;
    public static final int SIZEY = 20;
    public static final int XRATE = 2;
    public static final int YRATE = 2;
    public static final Color COLOR = new Color(0,0,0);


    private int xcoord;
    private int ycoord;
    private String dir;

    //EFFECT: Construct a bullet at xy coordinate with direction
    public Bullets(int x, int y, String dir) {
        this.xcoord = x;
        this.ycoord = y;
        this.dir = dir;

    }

    // MODIFY: this
    //EFFECT: Move the bullet in the direction it is already in
    public void move() {
        if (dir == "DOWN") {
            ycoord = ycoord - YRATE;
        } else if (dir == "UP") {
            ycoord = ycoord + YRATE;
        } else if (dir == "RIGHT") {
            xcoord = xcoord - XRATE;
        } else {
            xcoord = xcoord + XRATE;
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

    //EFFECT: Return direction of bullet
    public String getDir() {
        return dir;
    }

}
