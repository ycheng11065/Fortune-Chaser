package model;

import org.json.JSONObject;

import java.awt.*;

/**
 * Represents a treasure at pos x y
 */

public class Treasure {
    public static final int SIZEX = 20;
    public static final int SIZEY = 20;
    public static final Color COLOUR = new Color(255, 255, 51);

    private int xcoord;
    private int ycoord;
    private String msg;

    // EFFECTS: Creates a treasure at x y
    public Treasure(int x, int y) {
        xcoord = x;
        ycoord = y;
    }

    //EFFECT: Return true if Player touches treasure
    public boolean hit(Player p) {
        Rectangle treasureRectangle = new Rectangle(getX() - SIZEX / 2, getY() - SIZEY / 2,
                SIZEX, SIZEY);
        Rectangle playerRectangle = new Rectangle(p.getXcoord() - Player.SIZEX / 2,
                p.getYcoord() - Player.SIZEY / 2, Player.SIZEX, Player.SIZEY);
        return treasureRectangle.intersects(playerRectangle);

    }

    // EFFECTS: Return x
    public int getX() {
        return xcoord;
    }

    // EFFECTS: Return y
    public int getY() {
        return ycoord;
    }

    // MODIFIES: this
    // EFFECTS: Set msg
    public void addMsg(String msg) {
        this.msg = msg;
    }

    // EFFECTS: Return msg
    public String getMsg() {
        return msg;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Fortune", msg);
        return json;
    }
}
