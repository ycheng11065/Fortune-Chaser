package persistence;

import model.Coordinate;
import model.GameFile;
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
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            GameFile gf = reader.read();
            assertEquals("My gamefile", gf.getName());
            assertEquals(0, gf.getCoordinate().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            GameFile gf = reader.read();
            assertEquals("MY gamefile", gf.getName());
            List<String> cord = gf.getCoordinate();
            assertEquals(2, cord.size());
            checkGameFile("(2,2)", gf.getCoordinate().get(0));
            checkGameFile("(7,9)", gf.getCoordinate().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}