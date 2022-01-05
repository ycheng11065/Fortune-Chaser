package model;

import manager.ImageInventory;
import org.json.JSONObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents a treasure at pos x y
 */

public class Treasure extends Consumables {
    public static final Color COLOUR = new Color(255, 255, 51);
    private String msg;

    // EFFECTS: Creates a treasure at x y
    public Treasure(int x, int y) {
        super(x, y);
        this.image = ImageInventory.getCookie();
        this.name = "Treasure";
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
