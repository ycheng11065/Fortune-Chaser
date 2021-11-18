package model;

import java.awt.*;

// SOURCE: SpaceInvaderBase

public class Player {

    public static final int HEALTH = 200;
    public static final int SIZEX = 20; // Appearance will be changed
    public static final int SIZEY = 20; // into animated image?
    public static final int Y_POS = Frame.HEIGHT - 40; // need to change?
    public static final Color COLOUR = new Color(153, 153, 255);
    public static final int RATEY = 5;
    public static final int RATEX = 5;


    private int health;
    private int xcoord;
    private int ycoord;


    //EFFECT: Creates the player at the desired x y coordinate with health and direction
    public Player(int x, int y, int h) {
        this.xcoord = x;
        this.ycoord = y;
        this.health = h;
    }

    public void moveX(int vel) {
        xcoord += vel;
        xboundary();
    }

    public void moveY(int vel) {
        ycoord += vel;
        yboundary();
    }

    //MODIFY: this
    //EFFECT: Constrain player from leaving game boundary on x axis
    private void xboundary() {
        if (xcoord < 10) {
            xcoord = 10;
        } else if (xcoord > Frame.WIDTH - 10) {
            xcoord = Frame.WIDTH - 10;
        }

    }

    //MODIFY: this
    //EFFECT: Constrain player from leaving game boundary on y axis
    public void yboundary() {
        if (ycoord < 10) {
            ycoord = 10;
        } else if (ycoord > Frame.HEIGHT - 10) {
            ycoord = Frame.HEIGHT - 10;
        }

    }

    //MODIFY: this
    //EFFECT: Apply damage to player health
    public void healthDmg(int d) {
        health = health - d;
    }

    //EFFECT: Return x coordinate
    public int getXcoord() {
        return xcoord;
    }

    //EFFECT: Return y coordinate
    public int getYcoord() {
        return ycoord;
    }

    //EFFECT: Return player health
    public int getHealth() {
        return health;
    }


    public void eat() {
        health = HEALTH;
    }

    public void movePlayer(int x, int y) {
        xcoord = x;
        ycoord = y;
    }

}
