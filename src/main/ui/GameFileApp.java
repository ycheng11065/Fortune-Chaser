package ui;

import model.GameFile;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Represents the workroom application
public class GameFileApp {

    private static final int Interval = 10;
    private static final String JSON_STORE = "./data/gameFile.json";
    private Scanner input;
    private GameFile gameFile;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Zombie game;
    private int loadX;
    private int loadY;

    // EFFECTS: constructs workroom and runs application
    public GameFileApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        gameFile = new GameFile("My gamefile");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        game = new Zombie();
        runGameFile();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGameFile() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tw -> go up");
        System.out.println("\ta -> go left");
        System.out.println("\td -> go right");
        System.out.println("\ts -> go back");
        System.out.println("\tg -> shoot");
        System.out.println("\to -> save game progress to file");
        System.out.println("\tp -> load game from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
        if (command.equals("a")) {
            command(command);
        } else if (command.equals("w")) {
            command(command);
        } else if (command.equals("d")) {
            command(command);
        } else if (command.equals("s")) {
            command(command);
        } else if (command.equals("a")) {
            command(command);
        } else if (command.equals("g")) {
            game.play(command);
        } else if (command.equals("o")) {
            saveGameFile();
        } else if (command.equals("p")) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void command(String command) {
        game.play(command);
        addCoordinate(game.getMap().getPlayer().getXcoord(), game.getMap().getPlayer().getYcoord());
    }

    // MODIFIES: this
    // EFFECTS: prompt user for name and category of thingy and adds to workroom
    private void addCoordinate(int xcoord, int ycoord) {
        gameFile.addCoordinate(xcoord, ycoord);
    }


    // EFFECTS: saves the workroom to file
    private void saveGameFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameFile);
            jsonWriter.close();
            if (gameFile.getCoordinate().size() > 0) {
                String sav = gameFile.getCoordinate().get(gameFile.getCoordinate().size() - 1);
                String rig = sav.substring(1, sav.indexOf(","));
                String left = sav.substring(sav.indexOf(",") + 1, sav.indexOf(")"));
                loadX = Integer.parseInt(rig);
                loadY = Integer.parseInt(left);

                System.out.println("Saved " + gameFile.getName() + " to " + JSON_STORE);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads gamefile from file
    private void loadWorkRoom() {
        try {
            gameFile = jsonReader.read();
            System.out.println("Loaded " + gameFile.getName() + " from " + JSON_STORE);
            game.getMap().spawnChange(loadX, loadY);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
