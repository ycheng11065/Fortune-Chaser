package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestEnemies {

    private static final int XSPAWN = Map.WIDTH / 2;
    private static final int YSPAWN = Map.HEIGHT / 2;

    Enemies en1;

    @BeforeEach
    public void runBefore() {
        en1 = new Enemies(XSPAWN, YSPAWN, 20, "UP");
    }

    @Test
    public void moveTest() {
        Player p1 = new Player(20, 20, 20, "LEFT");
        en1.move(p1);
        float a1 = (float)Math.atan2(20 - XSPAWN, 20 - YSPAWN);
        assertEquals((int)(XSPAWN + 2 * Math.cos(a1)), en1.getXcoord());
        assertEquals(en1.getYcoord(), (int)(YSPAWN + 2 *Math.sin(a1)));

        Player p2 = new Player(0, 0, 20, "UP");
        Enemies en2 = new Enemies(5, 5, 20, "DOWN");
        en2.move(p2);
        float a2 = (float)Math.atan2(0 - XSPAWN, 0 - YSPAWN);
        assertEquals((int)(5 + 2 *Math.cos(a2)), en2.getXcoord());
        assertEquals((int)(5 + 2 *Math.sin(a2)), en2.getYcoord());

    }

    @Test
    public void isHitTestFalse() {
        Bullets b1 = new Bullets(0, 0, "UP");

        assertFalse(en1.hit(b1));


        Bullets b2 = new Bullets(en1.getXcoord() + Enemies.SIZEX / 2 + Bullets.SIZEX / 2, en1.getYcoord(), "UP");
        assertFalse(en1.hit(b2));

        Bullets e3 = new Bullets(en1.getXcoord(), en1.getYcoord() + Enemies.SIZEX / 2 + Bullets.SIZEX / 2,"UP");
        assertFalse(en1.hit(e3));

    }

    @Test
    public void isHitTestTrue() {
        Bullets b1 = new Bullets(en1.getXcoord(), en1.getYcoord(), "UP");
        assertTrue(en1.hit(b1));

//        Bullets b2 = new Bullets(en1.getXcoord() - Enemies.SIZEX / 2 - Bullets.SIZEX / 2,
//                en1.getYcoord(),"UP");
//        assertTrue(en1.hit(b2));

//        Bullets b3 = new Bullets(en1.getXcoord(), en1.getYcoord() - Enemies.SIZEX / 2 - Bullets.SIZEX / 2,"UP");
//        assertTrue(en1.hit(b3));

    }

    @Test
    public void healDmgTest() {
        en1.healthDmg(7);
        assertEquals(en1.getHealth(), 13);

        en1.healthDmg(21);
        assertEquals(en1.getHealth(), -8);

    }

}