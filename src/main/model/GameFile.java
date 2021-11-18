package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Source: JsonSerializationDemo

// Represents a game file having a collection of entity and its coordinate
public class GameFile implements Writable {
    private List<Treasure> treasures;
    private String name;

    // EFFECTS: constructs gamefile with a name and empty list of entities
    public GameFile(String name) {
        this.name = name;
        treasures = new ArrayList<>();
    }

    // EFFECTS: return name
    public String getName() {
        return name;
    }

    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    public List<Treasure> getTreasures() {
        return treasures;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Fortune", treasureToJson());
        return json;
    }

    // EFFECTS: Return coordinate as jsonArray
    private JSONArray treasureToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Treasure next: treasures) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }

    public void clearJson() {
        List<Treasure> clear = new ArrayList<>();
        treasures = clear;
    }
}


