package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TestFrame {
     Frame frame;

    @BeforeEach
    public void runBefore() {
        frame = new Frame();
    }

    @Test
    public void startTest() {
        frame.start();
        assertEquals(frame.getPlayer().getYcoord(), frame.HEIGHT / 2);
        assertEquals(frame.getPocket().getPocket().size(), 0);
        assertEquals(frame.getFortune().size(), 42);
        assertTrue(frame.getTreasure().getMsg() == null);
        assertFalse(frame.getisGameOver());
        assertEquals(frame.getFoodScore(), 0);
        assertEquals(frame.getTreasureScore(), 0);
    }

    @Test
    public void gameOverUpdateTest() {
        frame.start();
        frame.getPlayer().healthDmg(frame.getPlayer().HEALTH);
        frame.update();
        assertTrue(frame.getisGameOver());
    }

    @Test
    public void hungerUpdateTest() {
        frame.start();
        frame.update();
        assertEquals(frame.getPlayer().getHealth(), frame.getPlayer().HEALTH - 1);
    }

    @Test
    public void updateMovementTest() {
        frame.start();
        frame.setVelX(1);
        frame.setVelY(1);

        frame.update();
        assertEquals(frame.getPlayer().getXcoord(), frame.WIDTH / 2 + 1);
        assertEquals(frame.getPlayer().getYcoord(), frame.HEIGHT / 2 + 1);

        frame.setVelX(-2);
        frame.setVelY(-2);
        frame.update();
        assertEquals(frame.getPlayer().getXcoord(), frame.WIDTH / 2 - 1);
        assertEquals(frame.getPlayer().getYcoord(), frame.HEIGHT / 2 - 1);

    }

    @Test
    public void updateEatUpdateTest() {
        frame.start();

        frame.setFood(20, 20);
        frame.getPlayer().healthDmg(50);
        frame.update();
        assertEquals(frame.getPlayer().getHealth(), frame.getPlayer().HEALTH - 50 - frame.HUNGER_DMG);
        assertEquals(frame.getFoodScore(), 0);

        frame.setFood(Frame.WIDTH / 2, Frame.HEIGHT / 2);
        frame.update();
        assertEquals(frame.getPlayer().getHealth(), frame.getPlayer().HEALTH - frame.HUNGER_DMG);
        assertEquals(frame.getFoodScore(), 1);

    }

    @Test
    public void canPickUpUpdateTest() {
        frame.start();

        frame.setTreasure(20, 20);
        frame.update();
        assertEquals(frame.getTreasureScore(), 0);

        frame.setTreasure(Frame.WIDTH / 2, Frame.HEIGHT / 2);
        frame.update();
        assertEquals(frame.getTreasureScore(), 1);
    }

    @Test
    public void keyPressedTest() {
        frame.keyPressed(KeyEvent.VK_W);
        assertEquals(frame.getVelY(), -1 * frame.getPlayer().RATEX);
        frame.keyPressed(KeyEvent.VK_S);
        assertEquals(frame.getVelY(), frame.getPlayer().RATEX);
        frame.keyPressed(KeyEvent.VK_A);
        assertEquals(frame.getVelX(), -1 * frame.getPlayer().RATEY);
        frame.keyPressed(KeyEvent.VK_D);
        assertEquals(frame.getVelX(), frame.getPlayer().RATEY);

    }


    @Test
    public void keyReleasedTest() {
        frame.keyReleased(KeyEvent.VK_W);
        assertEquals(frame.getVelY(), 0);
        frame.keyReleased(KeyEvent.VK_S);
        assertEquals(frame.getVelY(), 0);
        frame.keyReleased(KeyEvent.VK_A);
        assertEquals(frame.getVelX(), 0);
        frame.keyReleased(KeyEvent.VK_D);
        assertEquals(frame.getVelX(), 0);
    }

    @Test
    public void playerHunger() {
        frame.playerHunger();
        assertEquals(frame.getPlayer().getHealth(), frame.getPlayer().HEALTH - Frame.HUNGER_DMG);

    }

    @Test
    public void gameOverTest() {
        frame.getPlayer().healthDmg(Player.HEALTH - 20);
        frame.gameOver();
        assertFalse(frame.getisGameOver());
        frame.getPlayer().eat();
        frame.getPlayer().healthDmg(Player.HEALTH);
        frame.gameOver();
        assertTrue(frame.getisGameOver());
    }

    @Test
    public void spawnFoodTest() {
        frame.spawnFood();
        assertTrue(frame.getFood().getX() < frame.WIDTH - 10 && frame.getFood().getX() > 10);
        assertTrue(frame.getFood().getY() < frame.HEIGHT - 10 && frame.getFood().getY() > 10);
        assertFalse(frame.getFood().getX() == frame.getPlayer().getXcoord());
        assertFalse(frame.getFood().getY() == frame.getPlayer().getYcoord());



    }

    @Test
    public void spawnTreasureTest() {
        frame.spawnTreasure();
        assertTrue(frame.getTreasure().getX() < frame.WIDTH - 10 && frame.getTreasure().getX() > 10);
        assertTrue(frame.getTreasure().getY() < frame.HEIGHT - 10 && frame.getTreasure().getY() > 10);
        assertFalse(frame.getTreasure().getX() == frame.getPlayer().getXcoord());
        assertFalse(frame.getTreasure().getY() == frame.getPlayer().getYcoord());

    }

    @Test
    public void canEatTest() {
        frame.spawnFood();
        assertFalse(frame.canEat());
        assertFalse(frame.getFood().hit(frame.getPlayer()));
        frame.getPlayer().movePlayer(frame.getFood().getX(), frame.getFood().getY());
        assertTrue(frame.canEat());
        assertTrue(frame.getFood().hit(frame.getPlayer()));
    }

    @Test
    public void canPickUpTest() {
        frame.spawnTreasure();
        assertFalse(frame.canPickUp());
        assertFalse(frame.getTreasure().hit(frame.getPlayer()));
        frame.getPlayer().movePlayer(frame.getTreasure().getX(), frame.getTreasure().getY());
        assertTrue(frame.canPickUp());
        assertTrue(frame.getTreasure().hit(frame.getPlayer()));
    }

    @Test
    public void setMsgTest() {
        ArrayList<String> temp = new ArrayList<>();
        frame.setMsg();
        assertEquals(frame.getFortune().size(), 41);
        assertTrue(frame.getTreasure().getMsg() != null);
        for (String next: frame.getFortune()) {
            temp.add(next);
        }
        frame.getFortune().removeAll(temp);
        assertEquals(frame.getFortune().size(), 0);
        frame.setMsg();
        assertEquals(frame.getTreasure().getMsg(), "Out of fortune");
    }

    @Test
    public void getTreasureTest() {
        frame.setTreasure(20, 20);
        assertTrue(frame.getTreasure() != null);
        assertEquals(frame.getTreasure().getX(), 20);
        assertEquals(frame.getTreasure().getY(), 20);
    }

    @Test
    public void setTreasureTest() {
        frame.setTreasure(50, 50);
        assertEquals(frame.getTreasure().getX(), 50);
        assertEquals(frame.getTreasure().getY(), 50);

    }




}
