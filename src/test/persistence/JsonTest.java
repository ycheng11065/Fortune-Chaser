package persistence;

import model.Coordinate;
import model.GameFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGameFile(String coord1, String coord2) {
        assertEquals(coord1, coord2);
    }
}