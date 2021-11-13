//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestFrame {
//
//    ArrayList<Bullets> bullets;
//    ArrayList<Enemies> enemies;
//
//    Bullets b1;
//    Bullets b2;
//    Bullets b3;
//    Bullets b4;
//    Bullets b5;
//    Bullets b6;
//    Bullets b7;
//    Bullets b8;
//    Bullets b9;
//    Bullets b10;
//    Bullets b11;
//
//     Frame map;
//
//    @BeforeEach
//    public void runBefore() {
//        map = new Frame();
//        bullets = new ArrayList<Bullets>();
//        enemies = new ArrayList<Enemies>();
//        b1 = new Bullets(0, 0, "UP");
//        b2 = new Bullets(0, -1, "DOWN");
//        b3 = new Bullets(0, Frame.HEIGHT + 1, "RIGHT");
//        b4 = new Bullets(-1, 0, "LEFT");
//        b5 = new Bullets(Frame.WIDTH + 1, 0, "UP");
//        b6 = new Bullets(0, 0, "DOWN");
//        b7 = new Bullets(30, 30, "RIGHT");
//        b8 = new Bullets(40, 40, "LEFT");
//        b9 = new Bullets(50, 50, "DOWN");
//        b10 = new Bullets(60, 60, "UP");
//        b11 = new Bullets(0, 0, "LEFT");
//
//    }
//
//    @Test
//    public void updateTest() {
//        Enemies en2 = new Enemies(5, 5, 20, "DOWN");
//        int en2dx = map.getPlayer().getXcoord() - en2.getXcoord();
//        int en2dy = map.getPlayer().getYcoord() - en2.getYcoord();
//
//        float a2 = (float)Math.atan2(en2dx, en2dy);
//
//        map.getEnemies().add(en2);
//
//        map.update();
//
//        assertEquals((int)(5 + en2.RATEX *Math.cos(a2)), en2.getXcoord());
//        assertEquals((int)(5 + en2.RATEY *Math.sin(a2)), en2.getYcoord());
//
//
//    }
//
//    @Test
//    public void fire() {
//
//        Bullets ba = new Bullets(map.getPlayer().getXcoord(), map.getPlayer().getYcoord() + 1 / 2, "UP");
//        map.fire();
//        assertEquals(map.getBullets().get(0).getXcoord(), ba.getXcoord());
//        assertEquals(map.getBullets().get(0).getYcoord(), ba.getYcoord());
//        assertEquals(map.getBullets().get(0).getDir(), ba.getDir());
//
//        Bullets bb = new Bullets(map.getPlayer().getXcoord(), map.getPlayer().getYcoord() - 1 / 2, "DOWN");
//        map.getPlayer().playerChangeDirection("DOWN");
//        map.fire();
//        assertEquals(map.getBullets().get(1).getXcoord(), bb.getXcoord());
//        assertEquals(map.getBullets().get(1).getYcoord(), bb.getYcoord());
//        assertEquals(map.getBullets().get(1).getDir(), bb.getDir());
//
//        Bullets bc = new Bullets(map.getPlayer().getXcoord() - 1 / 2, map.getPlayer().getYcoord(), "RIGHT");
//        map.getPlayer().playerChangeDirection("RIGHT");
//        map.fire();
//        assertEquals(map.getBullets().get(2).getXcoord(), bc.getXcoord());
//        assertEquals(map.getBullets().get(2).getYcoord(), bc.getYcoord());
//        assertEquals(map.getBullets().get(2).getDir(), bc.getDir());
//
//        Bullets bd = new Bullets(map.getPlayer().getXcoord() + 1 / 2, map.getPlayer().getYcoord(), "LEFT");
//        map.getPlayer().playerChangeDirection("LEFT");
//        map.fire();
//        assertEquals(map.getBullets().get(3).getXcoord(), bd.getXcoord());
//        assertEquals(map.getBullets().get(3).getYcoord(), bd.getYcoord());
//        assertEquals(map.getBullets().get(3).getDir(), bd.getDir());
//    }
//
////    @Test
////    public void movePlayerTest() {
////        map.movePlayer("UP");
////        assertEquals(map.getPlayer().getDirection(), "UP");
////        assertEquals(map.getPlayer().getYcoord(), 20 + map.getPlayer().RATEY);
////        assertEquals(map.getPlayer().getXcoord(), 20);
////
////        map.movePlayer("DOWN");
////        assertEquals(map.getPlayer().getDirection(), "DOWN");
////        assertEquals(map.getPlayer().getYcoord(), 22 - map.getPlayer().RATEY);
////        assertEquals(map.getPlayer().getXcoord(), 20);
////
////        map.movePlayer("RIGHT");
////        assertEquals(map.getPlayer().getDirection(), "RIGHT");
////        assertEquals(map.getPlayer().getYcoord(), 20);
////        assertEquals(map.getPlayer().getXcoord(), 20 - map.getPlayer().RATEX);
////
////        map.movePlayer("LEFT");
////        assertEquals(map.getPlayer().getDirection(), "LEFT");
////        assertEquals(map.getPlayer().getYcoord(), 20);
////        assertEquals(map.getPlayer().getXcoord(), 18 + map.getPlayer().RATEX);
////
////
////    }
//
//    @Test
//    public void moveBulletTest() {
//        map.getBullets().add(b1);
//        map.getBullets().add(b7);
//        map.getBullets().add(b8);
//        map.getBullets().add(b9);
//
//        map.moveBullet();
//
//        assertEquals(b1.getXcoord(), 0);
//        assertEquals(b1.getYcoord(), 2);
//
//        assertEquals(b7.getXcoord(), 28);
//        assertEquals(b7.getYcoord(), 30);
//
//        assertEquals(b8.getXcoord(), 42);
//        assertEquals(b8.getYcoord(), 40);
//
//        assertEquals(b9.getXcoord(), 50);
//        assertEquals(b9.getYcoord(), 48);
//
//    }
//
//    @Test
//    public void moveEnemiesTest() {
//        Enemies en1 = new Enemies(Frame.WIDTH / 2, Frame.HEIGHT / 2, 20, "UP");
//        Enemies en2 = new Enemies(5, 5, 20, "DOWN");
//
//        int en1dx = map.getPlayer().getXcoord() - en1.getXcoord();
//        int en1dy = map.getPlayer().getYcoord() - en1.getYcoord();
//
//        float a1 = (float)Math.atan2(en1dx, en1dy);
//
//        int en2dx = map.getPlayer().getXcoord() - en2.getXcoord();
//        int en2dy = map.getPlayer().getYcoord() - en2.getYcoord();
//
//        float a2 = (float)Math.atan2(en2dx, en2dy);
//
//
//        map.getEnemies().add(en1);
//        map.getEnemies().add(en2);
//
//        map.moveEnemies();
//
//        assertEquals((int)(Frame.WIDTH / 2 + en1.RATEX *Math.cos(a1)), en1.getXcoord());
//        assertEquals((int)(Frame.HEIGHT / 2 + en1.RATEY *Math.sin(a1)), en1.getYcoord());
//
//        assertEquals((int)(5 + en2.RATEX *Math.cos(a2)), en2.getXcoord());
//        assertEquals((int)(5 + en2.RATEY *Math.sin(a2)), en2.getYcoord());
//
//    }
//
//    @Test
//    public void bulletBoundaryTest() {
//
//        map.getBullets().add(b1);
//        map.getBullets().add(b2);
//        map.getBullets().add(b3);
//        map.getBullets().add(b4);
//        map.getBullets().add(b5);
//        map.bulletBoundary();
//
//        assertTrue(map.getBullets().contains(b1));
//        assertFalse(map.getBullets().contains(b2));
//        assertFalse(map.getBullets().contains(b3));
//        assertFalse(map.getBullets().contains(b4));
//        assertFalse(map.getBullets().contains(b5));
//    }
//
//    @Test
//    public void spawnTest() {
//        map.spawnEnemies();
//        map.spawnEnemies();
//        assertEquals(map.getEnemies().size(), 2);
//        map.spawnEnemies();
//        map.spawnEnemies();
//        assertEquals(map.getEnemies().size(), 4);
//
//    }
//
//    @Test
//    public void randomizerTest() {
//        ArrayList<Enemies> enemiesArrayList = new ArrayList<Enemies>();
//        enemiesArrayList.add(map.TOP_ENEMY);
//        enemiesArrayList.add(map.BOT_ENEMY);
//        enemiesArrayList.add(map.RIG_ENEMY);
//        enemiesArrayList.add(map.LEF_ENEMY);
//
//        map.randomizer();
//
//        Enemies a = map.getEnemies().get(0);
//
//        assertEquals(a, map.randomTest(enemiesArrayList, a));
//
//        map.randomizer();
//
//        Enemies b = map.getEnemies().get(0);
//
//        assertEquals(b, map.randomTest(enemiesArrayList, b));
//
//        map.randomizer();
//
//        Enemies c = map.getEnemies().get(0);
//
//        assertEquals(c, map.randomTest(enemiesArrayList, c));
//
//        map.randomizer();
//
//        Enemies d = map.getEnemies().get(0);
//
//        assertEquals(d, map.randomTest(enemiesArrayList, d));
//    }
//
//    @Test
//    public void hitBulletTest() {
//        Enemies en1 = new Enemies(0, 0, 20, "D0WN");
//        Enemies en2 = new Enemies(30, 30, 10, "D0WN");
//
//
//        List<Bullets> bulletsRemove = new ArrayList<Bullets>();
//        List<Enemies> enemiesRemove = new ArrayList<Enemies>();
//
//        map.getEnemies().add(en1);
//        map.getEnemies().add(en2);
//
//        map.getBullets().add(b1);
//        map.getBullets().add(b2);
//        map.getBullets().add(b3);
//        map.getBullets().add(b4);
//        map.getBullets().add(b5);
//        map.getBullets().add(b6);
//        map.getBullets().add(b7);
//
//        map.hitBullet();
//        map.hitBullet();
//
//        assertEquals(map.getEnemies().size(), 0);
//        assertEquals(map.getBullets().size(), 4);
//
//
//
//    }
//
//
//    @Test
//    public void enemiesCheckTrueTest() {
//        Enemies en1 = new Enemies(0, 0, 20, "D0WN");
//        Enemies en2 = new Enemies(30, 30, 20, "D0WN");
//
//        ArrayList<Bullets> removeBullet = new ArrayList<Bullets>();
//        map.getBullets().add(b1);
//        map.getBullets().add(b2);
//        map.getBullets().add(b3);
//        map.getBullets().add(b4);
//        map.getBullets().add(b5);
//        map.getBullets().add(b6);
//        map.getBullets().add(b7);
//
//        assertTrue(map.enemiesHitCheck(en1, removeBullet));
//        assertTrue(map.enemiesHitCheck(en2, removeBullet));
//        assertEquals(removeBullet.size(), 2);
//
//    }
//
//    @Test
//    public void enemiesCheckFalseTest() {
//        Enemies en1 = new Enemies(70, 70, 20, "D0WN");
//
//        ArrayList<Bullets> removeBullet = new ArrayList<Bullets>();
//        map.getBullets().add(b2);
//        map.getBullets().add(b3);
//        map.getBullets().add(b4);
//        map.getBullets().add(b5);
//
//        assertFalse(map.enemiesHitCheck(en1, removeBullet));
//    }
//
//    @Test
//    public void gameOverTest() {
//        Enemies en1 = new Enemies(0, 0, 20, "D0WN");
//        Enemies en2 = new Enemies(30, 30, 20, "D0WN");
//
//        map.getEnemies().add(en1);
//        map.getEnemies().add(en2);
//
//        map.getBullets().add(b1);
//        map.getBullets().add(b2);
//        map.getBullets().add(b3);
//
//
//        map.gameOver();
//        assertEquals(map.getisGameOver(), false);
//        assertEquals(map.getEnemies().size(), 2);
//        assertEquals(map.getBullets().size(), 3);
//
//        map.getPlayer().healthDmg(60);
//        map.gameOver();
//        assertEquals(map.getisGameOver(), true);
//        assertEquals(map.getEnemies().size(), 0);
//        assertEquals(map.getBullets().size(), 0);
//    }
//
//}
