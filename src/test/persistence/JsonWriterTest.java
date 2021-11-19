package persistence;

import model.GameFile;
import model.Treasure;
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
    void testWriterEmptyGamefile() {
        try {
            GameFile gf = new GameFile("My gamefile");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGameFile.json");
            writer.open();
            writer.write(gf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGameFile.json");
            gf = reader.read();
            assertEquals("My gamefile", gf.getName());
            assertEquals(0, gf.getTreasures().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGameFile() {
        try {
            GameFile gf = new GameFile("My gamefile");
            Treasure t1 = new Treasure(20, 20);
            t1.addMsg("Hello");
            Treasure t2 = new Treasure(25, 25);
            t2.addMsg("Hi");
            gf.addTreasure(t1);
            gf.addTreasure(t2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameFile.json");
            writer.open();
            writer.write(gf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGameFile.json");
            gf = reader.read();
            assertEquals("My gamefile", gf.getName());
            List<Treasure> fortune = gf.getTreasures();
            assertEquals(2, fortune.size());
            checkGameFile("Hello", fortune.get(0));
            checkGameFile("Hi", fortune.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}