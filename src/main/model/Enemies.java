package model;

import com.sun.glass.ui.Size;

import java.awt.*;

public class Enemies {

    public static final int HEALTH = 30;
    public static final int SIZEX = 60; // Appearance will be changed
    public static final int SIZEY = 60; // into animated image?
    public static final Color COLOUR = new Color(0, 255, 0);
    public static final int RATEY = 2;
    public static final int RATEX = 2;


    private int health;
    private int xcoord;
    private int ycoord;
    private String direction;

    //EFFECT: Create enemy
    public Enemies(int x, int y, int h, String dir) {
        this.xcoord = x;
        this.ycoord = y;
        this.health = h;
        this.direction = dir;
    }

    //MODIFY: this
    //EFFECT: Move in the direction of player

    // Use triangles??
    public void move(Player p) {
        xcoord = p.getXcoord() - xcoord;
        ycoord = p.getYcoord() - ycoord;
    }

    //EFFECT: Return true if bullet hit enemy
    public boolean hit(Bullets b) {
        Rectangle enemyRectangle = new Rectangle(getXcoord() - SIZEX / 2, getYcoord() - SIZEY / 2,
                SIZEX, SIZEY);
        Rectangle bulletRectangle = new Rectangle(b.getXcoord() - Bullets.SIZEX / 2,
                b.getYcoord() - Bullets.SIZEY / 2, Bullets.SIZEX, Bullets.SIZEY);
        return enemyRectangle.intersects(bulletRectangle);

    }

    //MODIFY: this
    //EFFECT: Return health after damage
    public int healthDmg(int d) {
        return health - d;
    }

    //MODIFY: this
    //EFFECT: Constraint enemy inside game boundaries on x axis
    public void xboundary() {
        if (xcoord < 0) {
            xcoord = 0;
        } else if (xcoord > Map.WIDTH) {
            xcoord = Map.WIDTH;
        }
    }

    //MODIFY: this
    //EFFECT: Constraint enemy inside game boundaries on y axis
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

    //EFFECT: Return the x coordinate
    public int getXcoord() {
        return xcoord;
    }

    //EFFECT: Return the y coordinate
    public int getYcoord() {
        return ycoord;
    }

    //EFFECT: Return health of enemy
    public int getHealth() {
        return health;
    }
}
