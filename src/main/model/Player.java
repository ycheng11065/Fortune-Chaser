package model;

import java.awt.*;

// SOURCE: SpaceInvaderBase

public class Player {

    public static final int HEALTH = 200;
    public static final int SIZEX = 20; // Appearance will be changed
    public static final int SIZEY = 20; // into animated image?
    public static final Color COLOUR = new Color(153, 153, 255);
    public static final int RATEY = 2;
    public static final int RATEX = 2;


    private int health;
    private int xcoord;
    private int ycoord;
    private String direction;

    //EFFECT: Creates the player facing the front at the x and y coordinate
    public Player(int x, int y, int h, String d) {
        this.xcoord = x;
        this.ycoord = y;
        this.health = h;
        this.direction = d;
        direction = "UP";
    }

    //MODIFY: this
    //EFFECT: Player move up
    public void moveUp() {
        ycoord = ycoord + RATEY;
    }

    //MODIFY: this
    //EFFECT: Player move down
    public void moveDown() {
        ycoord = ycoord - RATEY;
    }

    //MODIFY: this
    //EFFECT: Player move right
    public void moveRight() {
        xcoord = xcoord + RATEX;
    }

    //MODIFY: this
    //EFFECT: Player move left
    public void moveLeft() {
        xcoord = xcoord - RATEY;
    }

//    //MODIFY: this
//    //EFFECT: Player face front
//    public void faceFront() {
//        direction = "Front";
//    }
//
//    //MODIFY: this
//    //EFFECT: Player face right
//    public void faceRight() {
//        direction = "Right";
//    }
//
//    //MODIFY: this
//    //EFFECT: Player face down
//    public void faceDown() {
//        direction = "Down";
//    }
//
//    //MODIFY: this
//    //EFFECT: Player face left
//    public void faceLeft() {
//        direction = "Left";
//    }

    //EFFECT: Return true if hit by enemies
    public boolean isHit() {
        Rectangle playerRectangle = new Rectangle(getXcoord() - SIZEX / 2,
                getXcoord() - SIZEY / 2, SIZEX, SIZEY);
        Rectangle enemyRectangle = new Rectangle(getXcoord() - SIZEX / 2,
                getXcoord() - SIZEY / 2, Enemies.SIZEX, Enemies.SIZEY);
        return playerRectangle.intersects(enemyRectangle);

    }


    //MODIFY: this
    //EFFECT: Constrain player from leaving game boundary on x axis
    public void xboundary() {
        if (xcoord < 0) {
            xcoord = 0;
        } else if (xcoord > Map.WIDTH) {
            xcoord = Map.WIDTH;
        }

    }

    //MODIFY: this
    //EFFECT: Constrain player from leaving game boundary on y axis
    public void yboundary() {
        if (ycoord < 0) {
            ycoord = 0;
        } else if (ycoord > Map.HEIGHT) {
            ycoord = Map.HEIGHT;
        }

    }

    //EFFECT: Returns true if health is above 0
    public boolean isAlive() {
        return (health > 0);
    }

    //EFFECT: Return x coordinate
    public int getXcoord() {
        return xcoord;
    }

    //EFFECT: Return y coordinate
    public int getYcoord() {
        return ycoord;
    }

    //EFFECT: Return direction
    public String getDirection() {
        return direction;
    }

    public int getHealth() {
        return health;
    }

}
