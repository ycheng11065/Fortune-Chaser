package model;

import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.Random;

public class Map {

    public static final int WIDTH = 1000;
    public static final int Height = 800;
    public static final int MAX_BULLETS = 40;
    public static final Random RAND = new Random();
    public static final int SPAWN_RATE = 5;

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
        Player ply = new Player(20, 20, 60);
        isGameOver = false;
        enemieScore = 0;
    }

    public void update() {

    }

    public void fire() {
        if (bullets.size() < MAX_BULLETS) {
            if (player.faceDown()) {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord(), "DOWN");
            } else if (player.faceFront()) {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord(), "UP");
            } else if (player.faceRight()) {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord(), "RIGHT");
            } else {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord(), "LEFT");
            }
        }

    }

    public void moveBullet() {

    }

    public void moveEnemies() {

    }

    public void bulletBoundary() {

    }

    public void spawnEnemies() {


    }

    public void hitBullet() {

    }

    public void enemiesCount() {

    }


    public void gameOver() {

    }
}
