package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTreasure {

    Treasure treasure;

    @BeforeEach
    public void runBefore() {
        treasure = new Treasure(20, 20);
    }

    @Test
    public void hitTest() {
        Player player = new Player(20, 20, 100);
        Player player2 = new Player(20 - treasure.SIZEX, 20, 100);
        Player player3 = new Player(20 - treasure.SIZEX + 1, 20, 100);
        Player player4 = new Player(20, 20 + treasure.SIZEY, 100);
        Player player5 = new Player(20, 20 + treasure.SIZEY - 1, 100);
        assertTrue(treasure.hit(player));
        assertFalse(treasure.hit(player2));
        assertTrue(treasure.hit(player3));

        assertFalse(treasure.hit(player4));
        assertTrue(treasure.hit(player5));
    }

    @Test
    public void addMsgTest() {
        treasure.addMsg("Hello");
        assertEquals(treasure.getMsg(), "Hello");
    }

}
