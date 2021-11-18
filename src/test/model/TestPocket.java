package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPocket {

    Pocket pocket;

    @BeforeEach
    public void runBefore() {
       pocket = new Pocket();
    }

    @Test
    public void addTreasureTest() {
        Treasure treasure = new Treasure(20, 20);
        pocket.addTreasure(treasure);
        assertEquals(pocket.getPocket().get(0), treasure);
    }
}
