//package model;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import persistence.Writable;
//
//import java.util.ArrayList;
//import java.util.List;
//
////Source: JsonSerializationDemo
//
//// Represents a game file having a collection of entity and its coordinate
//public class GameFile implements Writable {
//    private List<String> coordinate;
//    private String name;
//
//    // EFFECTS: constructs gamefile with a name and empty list of entities
//    public GameFile(String name) {
//        this.name = name;
//        coordinate = new ArrayList<>();
//    }
//
//    // EFFECTS: return name
//    public String getName() {
//        return name;
//    }
//
//    // EFFECTS: return the list of coordinate strings
//    public List<String> getCoordinate() {
//        return coordinate;
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds player coordinate to this game file
//    public void addCoordinate(int xcoord, int ycoord) {
//        String coord = "(" + Integer.toString(xcoord) + "," + Integer.toString(ycoord) + ")";
//        coordinate.add(coord);
//    }
//
//    @Override
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("name", name);
//        json.put("coordinate", coordinateToJson());
//        return json;
//    }

//    // EFFECTS: Return coordinate as jsonArray
//    private JSONArray coordinateToJson() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (String c : coordinate) {
//            jsonArray.put(c);
//        }
//
//        return jsonArray;
//    }
//}


