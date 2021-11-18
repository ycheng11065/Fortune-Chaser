package model;//package model;

import model.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestPlayer {
    private static final int XSPAWN = Frame.WIDTH / 2;
    private static final int YSPAWN = Frame.HEIGHT / 2;

    Player player;

    @BeforeEach
    public void runBefore() {
        player = new Player(XSPAWN, YSPAWN, Player.HEALTH);
    }

    @Test
    public void xboundaryTest() {
        Player p2 = new Player(Frame.WIDTH - 2, Frame.HEIGHT, 20);
        p2.moveX(-5);
        assertEquals(p2.getXcoord(), Frame.WIDTH - 10);
        Player p3 = new Player(2, Frame.HEIGHT, 20);
        p3.moveX(3);
        assertEquals(p3.getXcoord(), 10);
        Player p4 = new Player(2, Frame.HEIGHT, 20);
        p4.moveX(-9);
        assertEquals(p4.getXcoord(), 10);

    }

    @Test
    public void yboundaryTest() {
        Player p2 = new Player(Frame.WIDTH, Frame.HEIGHT - 2, 20);
        p2.moveY(5);
        assertEquals(p2.getYcoord(), Frame.HEIGHT - 10);
        Player p3 = new Player(Frame.WIDTH, 2, 20);
        p3.moveY(-5);
        assertEquals(p3.getYcoord(), 10);
        Player p4 = new Player(Frame.WIDTH, 2, 20);
        p4.moveY(-8);
        assertEquals(p4.getYcoord(), 10);

    }

    @Test
    public void getXcoordTest() {
        Player p2 = new Player(0, 0, 0);
        assertEquals(player.getXcoord(), XSPAWN);
        assertEquals(p2.getXcoord(), 0);

    }

    @Test
    public void getYcoordTest() {
        Player p2 = new Player(0, 0, 0);
        assertEquals(player.getYcoord(), YSPAWN);
        assertEquals(p2.getYcoord(), 0);

    }


    @Test
    public void getHealthTest() {
        Player p2 = new Player(0, 0, 0);
        assertEquals(player.getHealth(), 200);
        assertEquals(p2.getHealth(), 0);

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

    @Test
    public void eatTest() {
        player.healthDmg(20);
        player.eat();
        assertEquals(player.getHealth(), Player.HEALTH);
        player.healthDmg(100);
        player.eat();
        assertEquals(player.getHealth(), Player.HEALTH);
    }

}
