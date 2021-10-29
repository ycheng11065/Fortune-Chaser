package persistence;

import model.Coordinate;
import model.GameFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGameFile(String coord, String cord) {
        assertEquals(coord, cord);
    }
}