package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFood {

    Food food;

    @BeforeEach
    public void runBefore() {
        food = new Food(20, 20);
    }

    @Test
    public void hitTest() {
        Player player = new Player(20, 20, 100);
        Player player2 = new Player(20 - food.SIZEX, 20, 100);
        Player player3 = new Player(20 - food.SIZEX + 1, 20, 100);
        Player player4 = new Player(20, 20 + food.SIZEY, 100);
        Player player5 = new Player(20, 20 + food.SIZEY - 1, 100);
        assertTrue(food.hit(player));
        assertFalse(food.hit(player2));
        assertTrue(food.hit(player3));

        assertFalse(food.hit(player4));
        assertTrue(food.hit(player5));
    }

    @Test
    public void getxTest() {
        assertEquals(food.getX(), 20);
    }

    @Test
    public void getYTest() {
        assertEquals(food.getY(), 20);
    }
}
