package persistence;

import model.GameFile;
import model.Treasure;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGameFile(String msg, Treasure fortune) {
        assertEquals(msg, fortune.getMsg());
    }
}