package model;

import java.awt.*;

/**
 * Represents a player with a position a coordinate x y and health
 * Reference: Space Invader
 */

public class Player {

    public static final int HEALTH = 200;
    public static final int SIZEX = 20;
    public static final int SIZEY = 20;
    public static final Color COLOUR = new Color(153, 153, 255);
    public static final int RATEY = 5;
    public static final int RATEX = 5;

    private int health;
    private int xcoord;
    private int ycoord;

    //EFFECTS: Creates the player at the desired x y coordinate with health
    public Player(int x, int y, int h) {
        this.xcoord = x;
        this.ycoord = y;
        this.health = h;
    }

    //MODIFIES: this
    //EFFECTS: Move player horizontally in vel direction
    public void moveX(int vel) {
        xcoord += vel;
        xboundary();
    }

    //MODIFIES: this
    //EFFECTS: Move player vertically in vel direction
    public void moveY(int vel) {
        ycoord += vel;
        yboundary();
    }

    //MODIFIES: this
    //EFFECT: Constrain player from leaving game boundary on x axis
    private void xboundary() {
        if (xcoord < 10) {
            xcoord = 10;
        } else if (xcoord > Frame.WIDTH - 10) {
            xcoord = Frame.WIDTH - 10;
        }

    }

    //MODIFIES: this
    //EFFECTS: Constrain player from leaving game boundary on y axis
    public void yboundary() {
        if (ycoord < 10) {
            ycoord = 10;
        } else if (ycoord > Frame.HEIGHT - 10) {
            ycoord = Frame.HEIGHT - 10;
        }

    }

    //MODIFIES: this
    //EFFECTS: Apply damage to player health
    public void healthDmg(int d) {
        health = health - d;
    }

    //EFFECTS: Return x coordinate
    public int getXcoord() {
        return xcoord;
    }

    //EFFECTS: Return y coordinate
    public int getYcoord() {
        return ycoord;
    }

    //EFFECTS: Return player health
    public int getHealth() {
        return health;
    }

    //MODIFIES: this
    //EFFECTS: Put health back to full
    public void eat() {
        health = HEALTH;
    }

    //MODIFIES: this
    //EFFECTS: Move player to new x and y position
    public void movePlayer(int x, int y) {
        xcoord = x;
        ycoord = y;
    }

}
