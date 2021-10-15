package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestPlayer {
    private static final int XSPAWN = Map.WIDTH / 2;
    private static final int YSPAWN = Map.HEIGHT / 2;

    Player player;

    @BeforeEach
    public void runBefore() {
        player = new Player(XSPAWN, YSPAWN, Player.HEALTH, "UP");
    }

    @Test
    public void moveUpTest() {
        player.moveUp();
        assertEquals(player.getXcoord(), XSPAWN);
        assertEquals(player.getYcoord(), YSPAWN + player.RATEX);

        Player p1 = new Player(XSPAWN,Map.HEIGHT - 1, 20, "UP");
        p1.moveUp();
        assertEquals(p1.getXcoord(), XSPAWN);
        assertEquals(p1.getYcoord(), Map.HEIGHT);

    }

    @Test
    public void moveDownTest() {
        player.moveDown();
        assertEquals(player.getXcoord(), XSPAWN);
        assertEquals(player.getYcoord(), YSPAWN - player.RATEX);

        Player p1 = new Player(XSPAWN,1, 20, "DOWN");
        p1.moveDown();
        assertEquals(p1.getXcoord(), XSPAWN);
        assertEquals(p1.getYcoord(), 0);
    }

    @Test
    public void moveRightTest() {
        player.moveRight();
        assertEquals(player.getXcoord(), XSPAWN  - player.RATEX);
        assertEquals(player.getYcoord(), YSPAWN);

        Player p1 = new Player(1 ,YSPAWN, 20, "DOWN");
        p1.moveRight();
        assertEquals(p1.getXcoord(), 0);
        assertEquals(p1.getYcoord(), YSPAWN);
    }

    @Test
    public void moveLeftTest() {
        player.moveLeft();
        assertEquals(player.getXcoord(), XSPAWN  + player.RATEX);
        assertEquals(player.getYcoord(), YSPAWN);

        Player p1 = new Player(Map.WIDTH - 1 ,YSPAWN, 20, "DOWN");
        p1.moveLeft();
        assertEquals(p1.getXcoord(), Map.WIDTH);
        assertEquals(p1.getYcoord(), YSPAWN);
    }

    @Test
    public void isHitTestFalse() {
        Enemies e1 = new Enemies(0, 0, Enemies.HEALTH, "UP");
        assertFalse(player.isHit(e1));

        Enemies e2 = new Enemies(player.getXcoord() + Enemies.SIZEX / 2 + Bullets.SIZEX / 2,
                player.getYcoord(), Enemies.HEALTH, "UP");
        assertFalse(player.isHit(e1));

        Enemies e3 = new Enemies(player.getXcoord(), player.getYcoord() + Enemies.SIZEX / 2 + Bullets.SIZEX / 2,
                Enemies.HEALTH, "UP");
        assertFalse(player.isHit(e1));

    }

    @Test
    public void isHitTestTrue() {
        Enemies e1 = new Enemies(player.getXcoord(), player.getYcoord(), Enemies.HEALTH, "UP");
        assertTrue(player.isHit(e1));

        Enemies e2 = new Enemies(player.getXcoord() +  1, player.getYcoord() + 1, Enemies.HEALTH, "UP");
        assertTrue(player.isHit(e2));

        Enemies e3 = new Enemies(player.getXcoord() -  1, player.getYcoord() - 1, Enemies.HEALTH, "UP");
        assertTrue(player.isHit(e3));
    }

    @Test
    public void xboundaryTest() {
        Player p2 = new Player(Map.WIDTH - 2, Map.HEIGHT, 20, "UP");
        p2.moveLeft();
        p2.moveLeft();
        p2.moveLeft();
        assertEquals(p2.getXcoord(), Map.WIDTH);
        Player p3 = new Player(2, Map.HEIGHT, 20, "UP");
        p3.moveRight();
        p3.moveRight();
        p3.moveRight();
        assertEquals(p3.getXcoord(), 0);
        Player p4 = new Player(2, Map.HEIGHT, 20, "UP");
        p4.moveLeft();
        p4.moveLeft();
        p4.moveLeft();
        assertEquals(p4.getXcoord(), 8);

    }

    @Test
    public void yboundaryTest() {
        Player p2 = new Player(Map.WIDTH, Map.HEIGHT - 2, 20, "UP");
        p2.moveUp();
        p2.moveUp();
        p2.moveUp();
        assertEquals(p2.getYcoord(), Map.HEIGHT);
        Player p3 = new Player(Map.WIDTH, 2, 20, "UP");
        p3.moveDown();
        p3.moveDown();
        p3.moveDown();
        assertEquals(p3.getYcoord(), 0);
        Player p4 = new Player(Map.WIDTH, 2, 20, "UP");
        p4.moveUp();
        p4.moveUp();
        p4.moveUp();
        assertEquals(p4.getYcoord(), 8);

    }

    @Test
    public void getXcoordTest() {
        Player p2 = new Player(0, 0, 0, "DOWN");
        assertEquals(player.getXcoord(), XSPAWN);
        assertEquals(p2.getXcoord(), 0);

    }

    @Test
    public void getYcoordTest() {
        Player p2 = new Player(0, 0, 0, "DOWN");
        assertEquals(player.getYcoord(), YSPAWN);
        assertEquals(p2.getYcoord(), 0);

    }

    @Test
    public void getDirectionTest() {
        Player p2 = new Player(0, 0, 0, "DOWN");
        assertEquals(player.getDirection(), "UP");
        assertEquals(p2.getDirection(), "DOWN");

    }

    @Test
    public void getHealTest() {
        Player p2 = new Player(0, 0, 0, "DOWN");
        assertEquals(player.getHealth(), 200);
        assertEquals(p2.getHealth(), 0);

    }

    @Test
    public void changeDirection() {
        player.playerChangeDirection("UP");
        assertEquals(player.getDirection(), "UP");

        player.playerChangeDirection("DOWN");
        assertEquals(player.getDirection(), "DOWN");

        player.playerChangeDirection("RIGHT");
        assertEquals(player.getDirection(), "RIGHT");

        player.playerChangeDirection("LEFT");
        assertEquals(player.getDirection(), "LEFT");
    }

    @Test
    public void healDmgTest() {
        player.healthDmg(0);
        assertEquals(player.getHealth(), 200);
        player.healthDmg(2);
        assertEquals(player.getHealth(), 198);
        player.healthDmg(201);
        assertEquals(player.getHealth(), -3);
    }

}
