package model;

import com.sun.glass.ui.Size;

import java.awt.*;

// SOURCE: SpaceInvaderBase

public class Enemies {

    public static final int HEALTH = 30;
    public static final int SIZEX = 20; // Appearance will be changed
    public static final int SIZEY = 20; // into animated image?
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
    //EFFECT: Chase player
    public void move(Player p) {
        int xdif = 0;
        int ydif = 0;
        xdif = p.getXcoord() - xcoord;
        ydif = p.getYcoord() - ycoord;

        float angle = (float)Math.atan2(xdif, ydif); //Finds theta

        xcoord += RATEX * Math.cos(angle); // finds unknown x using hyp * sin
        ycoord += RATEX * Math.cos(angle); // finds unknown y using hyp * cos
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
    public void healthDmg(int d) {
        health = health - d;
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
