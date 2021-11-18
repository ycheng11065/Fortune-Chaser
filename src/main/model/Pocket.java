package model;

import org.json.JSONObject;

import java.util.ArrayList;

public class Pocket {
    private ArrayList<Treasure> pocket;

    public Pocket() {
        pocket = new ArrayList<>();
    }

    public void addTreasure(Treasure t) {
        pocket.add(t);
    }

    public ArrayList<Treasure> getPocket() {
        return pocket;
    }

}