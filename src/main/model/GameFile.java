package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Source: JsonSerializationDemo

// Represents a game file having a collection of entity and its coordinate
public class GameFile implements Writable {
    private List<Pocket> pocket;
    private String name;

    // EFFECTS: constructs gamefile with a name and empty list of entities
    public GameFile(String name) {
        this.name = name;
        pocket = new ArrayList<>();
    }

    // EFFECTS: return name
    public String getName() {
        return name;
    }

    public void addTreasure(Pocket treasure) {
        pocket.add(treasure);
    }

    public List<Pocket> getPocket() {
        return Collections.unmodifiableList(pocket);
    }

    public int numTreasure() {
        return pocket.size();
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

        for (Pocket next: pocket) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }
}


