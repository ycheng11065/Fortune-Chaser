//package persistence;
//
//import model.GameFile;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//import org.json.*;
//
//// Represents a reader that reads gameFile from JSON data stored in file
//public class JsonReader {
//    private String source;
//
//    // EFFECTS: constructs reader to read from source file
//    public JsonReader(String source) {
//        this.source = source;
//    }
//
//    // EFFECTS: reads gameFile from file and returns it;
//    // throws IOException if an error occurs reading data from file
//    public GameFile read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseGameFile(jsonObject);
//    }
//
//    // EFFECTS: reads source file as string and returns it
//    private String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//
//        return contentBuilder.toString();
//    }
//
//    // EFFECTS: parses gamefile from JSON object and returns it
//    private GameFile parseGameFile(JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        GameFile gf = new GameFile(name);
//        return gf;
//    }
//
//
//}
//
