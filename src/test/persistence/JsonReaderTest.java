package persistence;

import model.GameFile;
import model.Treasure;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameFile gf = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGameFile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameFile.json");
        try {
            GameFile gf = reader.read();
            assertEquals("My gamefile", gf.getName());
            assertEquals(0, gf.getTreasures().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGameFile() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameFile.json");
        try {
            GameFile gf = reader.read();
            assertEquals("My gamefile", gf.getName());
            List<Treasure> cord = gf.getTreasures();
            assertEquals(2, cord.size());
            checkGameFile("Hello", gf.getTreasures().get(0));
            checkGameFile("Hi", gf.getTreasures().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}