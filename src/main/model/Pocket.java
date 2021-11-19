package model;

import java.util.ArrayList;

/**
 * Represents a list of treasure
 */

public class Pocket {
    private ArrayList<Treasure> pocket;

    //EFFECTS: Create an arrayList to store treasure
    public Pocket() {
        pocket = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Add a treasure to pocket
    public void addTreasure(Treasure t) {
        pocket.add(t);
    }

    //EFFECTS: Return pocket
    public ArrayList<Treasure> getPocket() {
        return pocket;
    }

}