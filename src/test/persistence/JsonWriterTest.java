package persistence;

import model.GameFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            GameFile gf = new GameFile("My gamefile");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            GameFile gf = new GameFile("My gamefile");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(gf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            gf = reader.read();
            assertEquals("My gamefile", gf.getName());
            assertEquals(0, gf.getCoordinate().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            GameFile gf = new GameFile("My gamefile");
            gf.addCoordinate(3, 5);
            gf.addCoordinate(100, 200);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(gf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            gf = reader.read();
            assertEquals("My gamefile", gf.getName());
            List<String> coord = gf.getCoordinate();
            assertEquals(2, coord.size());
            checkGameFile("(3,5)", coord.get(0));
            checkGameFile("(100,200)", coord.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}