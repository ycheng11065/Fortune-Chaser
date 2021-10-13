package model;

import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final int MAX_BULLETS = 40;
    public static final int BULLET_DMG = 10;
    public static final Random RAND = new Random();
    public static final int SPAWN_RATE = 5;
    public static final Enemies TOP_ENEMY = new Enemies(WIDTH / 2, 1, 30, "DOWN");
    public static final Enemies BOT_ENEMY = new Enemies(WIDTH / 2, HEIGHT + 1, 30, "TOP");
    public static final Enemies RIG_ENEMY = new Enemies(1, HEIGHT / 2, 30, "RIGHT");
    public static final Enemies LEF_ENEMY = new Enemies(WIDTH - 1, HEIGHT / 2, 30, "LEFT");

    private ArrayList<Bullets> bullets;
    private ArrayList<Enemies> enemies;
    private Player player;
    private boolean isGameOver;
    private int enemieScore;

    //EFFECT: Create empty list of bullets and enemies, spawns the player
    public Map() {
        bullets = new ArrayList<Bullets>();
        enemies = new ArrayList<Enemies>();
        start();
    }

    public void start() {
        enemies.clear();
        bullets.clear();
        Player ply = new Player(20, 20, 60, "UP");
        isGameOver = false;
        enemieScore = 0;
    }

    public void update() {

    }

    public void fire() {
        if (bullets.size() < MAX_BULLETS) {
            if (player.getDirection() == "DOWN") {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord(), "DOWN");
            } else if (player.getDirection() == "UP") {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord(), "UP");
            } else if (player.getDirection() == "RIGHT") {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord(), "RIGHT");
            } else {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord(), "LEFT");
            }
        }

    }

    public void moveBullet() {
        for (Bullets next : bullets) {
            next.move();
        }

    }

//    public void moveEnemies() {
//        for (Enemies next : enemies) {
//            next.move();
//        }
//
//    }

    //MODIFY: this
    //EFFECT: If bullet leaves boundary then delete it
    public void bulletBoundary() {
        List<Bullets> bulletsBound = new ArrayList<Bullets>();

        for (Bullets next : bullets) {
            if (next.getYcoord() < 0 || next.getYcoord() > HEIGHT || next.getXcoord() < 0 || next.getXcoord() > WIDTH) {
                bulletsBound.add(next);
            }
        }

        bullets.removeAll(bulletsBound);

    }

    public void spawnEnemies() {
        while (isGameOver != false) {
            int a = 0;
            a++;
            if (a % 2000 == 0) {
                randomizer();
            }
        }

    }

    public void randomizer() {
        int randomNum = RAND.nextInt(3);

        if (randomNum == 0) {
            enemies.add(TOP_ENEMY);
        } else if (randomNum == 1) {
            enemies.add(BOT_ENEMY);
        } else if (randomNum == 2) {
            enemies.add(LEF_ENEMY);
        } else {
            enemies.add(RIG_ENEMY);
        }
    }

    //MODIFY: this
    //EFFECT: Remove bullets that have hit an enemy, if enemy health reach zero or below, remove it as well
    public void hitBullet() {
        List<Bullets> bulletsRemove = new ArrayList<Bullets>();
        List<Enemies> enemiesRemove = new ArrayList<Enemies>();

        for (Enemies next : enemies) {
            if (enemiesHitCheck(next, bulletsRemove)) {
                next.healthDmg(BULLET_DMG);
                if (next.getHealth() <= 0) {
                    enemiesRemove.add(next);
                    enemieScore++;
                }
            }
        }

        enemies.removeAll(enemiesRemove);
        bulletsRemove.removeAll(bulletsRemove);

    }

    //EFFECT: Check if bullet hit enemy, if so delete bullet
    public boolean enemiesHitCheck(Enemies e, List<Bullets> bulletsRemove) {
        for (Bullets next: bullets) {
            if (e.hit(next)) {
                bulletsRemove.add(next);
                return true;
            }
        }

        return false;
    }


    public void gameOver() {
        if (player.getHealth() <= 0) {
            isGameOver = true;

        }

        if (isGameOver) {
            enemies.clear();
            bullets.clear();
        }

    }
}
