package model;

import ui.Commands;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// SOURCE: SpaceInvaderBase

public class Frame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final int MAX_BULLETS = 10;
    public static final int MAX_ENEMIES = 10;
    public static final int BULLET_DMG = 10;
    public static final Random RAND = new Random();
    public static final int SPAWN_RATE = 5; // Millisecond
//    public static final Enemies TOP_ENEMY = new Enemies(WIDTH / 2, 1, 30, "DOWN");
//    public static final Enemies BOT_ENEMY = new Enemies(WIDTH / 2, HEIGHT + 1, 30, "TOP");
//    public static final Enemies RIG_ENEMY = new Enemies(1, HEIGHT / 2, 30, "RIGHT");
//    public static final Enemies LEF_ENEMY = new Enemies(WIDTH - 1, HEIGHT / 2, 30, "LEFT");

//    private ArrayList<Bullets> bullets;
//    private ArrayList<Enemies> enemies;
    private Player player;
    private boolean isGameOver;
    private int velX = 0;
    private int velY = 0;
    private int enemieScore;
    private int counter = 0;
    private int numItemPicked;

    //EFFECT: Create empty list of bullets and enemies, spawns the player
    public Frame() {
//        bullets = new ArrayList<Bullets>();
//        enemies = new ArrayList<Enemies>();
        start();
    }

    //MODIFY: this
    //EFFECT: Start game, clear screen, spawn player
    public void start() {
//        enemies.clear();
//        bullets.clear();
        player = new Player(WIDTH / 2, HEIGHT / 2, 60, "UP");
        isGameOver = false;
        enemieScore = 0;
    }

    //MODIFY: this
    //EFFECT: Update movement, Bullets and enemies
    public void update() {
//        Commands cmd = new Commands();
//        moveBullet();
//        spawnEnemies();
//        updateEnemiesLocation();
//        moveEnemies();
//        playerControl(cmd.nextCommand()); //delay player movement until next command
//
//        hitBullet();
//        bulletBoundary();
        player.moveX(velX);
        player.moveY(velY);
        System.out.println(player.getXcoord());
        System.out.println(player.getYcoord());
        gameOver();

    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            velY = player.RATEY;
        } else if (keyCode == KeyEvent.VK_S) {
            velY = -1 * player.RATEY;
        } else if (keyCode == KeyEvent.VK_A) {
            velX = -1 * player.RATEX;
        } else if (keyCode == KeyEvent.VK_D) {
            velX = player.RATEX;
        } else if (keyCode == KeyEvent.VK_R && isGameOver) {
            start();
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        }
    }

    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            velY = 0;
        } else if (keyCode == KeyEvent.VK_S) {
            velY = 0;
        } else if (keyCode == KeyEvent.VK_A) {
            velX = 0;
        } else if (keyCode == KeyEvent.VK_D) {
            velX = 0;
        }
    }

//    // EFFECTS: Move player based on inserted commands
//    public void playerControl(String cmd) {
//
//        if (cmd.equals("w")) {
//            player.playerChangeDirection("UP");
//            player.moveUp();
//            System.out.println("up");
//
//        } else if (cmd.equals("s")) {
//            player.playerChangeDirection("DOWN");
//            player.moveDown();
//            System.out.println("down");
//
//        } else if (cmd.equals("d")) {
//            player.playerChangeDirection("RIGHT");
//            player.moveRight();
//            System.out.println("right");
//
//        } else if (cmd.equals("a")) {
//            player.playerChangeDirection("LEFT");
//            player.moveLeft();
//            System.out.println("left");
//        }
//    }

    //MODIFY: this
    //EFFECT: Change value of isGameOver to true if player is dead, clear bullet and enemy
    // list if isGameOver is true
    public void gameOver() {
        if (player.getHealth() <= 0) {
            isGameOver = true;

        }

        if (isGameOver) {
//            enemies.clear();
//            bullets.clear();
        }

    }

    //EFFECT: Return player
    public Player getPlayer() {
        return player;
    }

    //EFFECT: Return true if game is over, false if game is not over
    public boolean getisGameOver() {
        return isGameOver;
    }

    //MODIFY: this
    //EFFECTS: Spawn player to new x and y coord
    public void spawnChange(int xccord, int ycoord) {
        player.playerChangeXcoord(xccord);
        player.playerChangeYcoord(ycoord);

    }

    public int getNumItemPicked() {
        return numItemPicked;
    }

}
