//package model;
//
//import org.json.JSONObject;
//import persistence.Writable;
//
//// Represents a coordinate having a name and a category
//public class Coordinate implements Writable {
//    private String name;
//
//    // EFFECTS: constructs a thingy with a name and category
//    public Coordinate(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("name", name);
//        return json;
//    }
//}
