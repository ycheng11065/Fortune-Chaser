package model;

import ui.Commands;
import ui.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// SOURCE: SpaceInvaderBase

public class Map {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final int MAX_BULLETS = 10;
    public static final int MAX_ENEMIES = 10;
    public static final int BULLET_DMG = 10;
    public static final Random RAND = new Random();
    public static final int SPAWN_RATE = 5; // Millisecond
    public static final Enemies TOP_ENEMY = new Enemies(WIDTH / 2, 1, 30, "DOWN");
    public static final Enemies BOT_ENEMY = new Enemies(WIDTH / 2, HEIGHT + 1, 30, "TOP");
    public static final Enemies RIG_ENEMY = new Enemies(1, HEIGHT / 2, 30, "RIGHT");
    public static final Enemies LEF_ENEMY = new Enemies(WIDTH - 1, HEIGHT / 2, 30, "LEFT");

    private ArrayList<Bullets> bullets;
    private ArrayList<Enemies> enemies;
    private Player player;
    private boolean isGameOver;
    private int enemieScore;
    private int counter = 0;

    //EFFECT: Create empty list of bullets and enemies, spawns the player
    public Map() {
        bullets = new ArrayList<Bullets>();
        enemies = new ArrayList<Enemies>();
        start();
    }

    //MODIFY: this
    //EFFECT: Start game, clear screen, spawn player
    public void start() {
        enemies.clear();
        bullets.clear();
        player = new Player(20, 20, 60, "UP");
        isGameOver = false;
        enemieScore = 0;
    }

    //MODIFY: this
    //EFFECT: Update movement, Bullets and enemies
    public void update() {
        Commands cmd = new Commands();
        moveBullet();
        spawnEnemies();
        updateEnemiesLocation();
        moveEnemies();
        playerControl(cmd.nextCommand()); //delay player movement until next command

        hitBullet();
        bulletBoundary();
        gameOver();

    }

    public void updateEnemiesLocation() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemies en = enemies.get(i);
            System.out.println(en.getXcoord());
            System.out.println(en.getYcoord());

        }
    }

    //MODIFY: this
    //EFFECT: Spawn bullets at player location and add it to bullet list
    public void fire() {
        if (bullets.size() < MAX_BULLETS) {
            if (player.getDirection() == "DOWN") {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord() - 1 / 2, "DOWN");
                bullets.add(b);
            } else if (player.getDirection() == "UP") {
                Bullets b = new Bullets(player.getXcoord(), player.getYcoord() + 1 / 2, "UP");
                bullets.add(b);
            } else if (player.getDirection() == "RIGHT") {
                Bullets b = new Bullets(player.getXcoord() - 1 / 2, player.getYcoord(), "RIGHT");
                bullets.add(b);
            } else {
                Bullets b = new Bullets(player.getXcoord() + 1 / 2, player.getYcoord(), "LEFT");
                bullets.add(b);
            }
        }

    }

    //EFFECT: Move the bullets inside list
    public void moveBullet() {
        for (Bullets next : bullets) {
            next.move();
        }

    }

    //EFFECT: Move the enemies inside the list
    public void moveEnemies() {
        for (Enemies next : enemies) {
            next.move(player);
        }

    }

    public void cmdReceived(String cmd) {
        if (cmd.equals("5")) {
            fire();
        } else {
            playerControl(cmd);
        }
    }

    public void playerControl(String cmd) {

        if (cmd.equals("1")) {
            player.playerChangeDirection("UP");
            player.moveUp();
            System.out.println("up");

        } else if (cmd.equals("2")) {
            System.out.println("down");

        } else if (cmd.equals("3")) {
            player.playerChangeDirection("RIGHT");
            player.moveRight();
            System.out.println("right");

        } else if (cmd.equals("4")) {
            player.playerChangeDirection("LEFT");
            player.moveLeft();
            System.out.println("left");

        } else {
            player.idle();
        }
    }

    public void playerMove() {


    }

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

    //MODIFIES: This
    //EFFECT: Spawn enemies every SPAWN_RATE milliseconds, stops when MAX_Enemies reached
    public void spawnEnemies() {
        while (isGameOver == false && enemies.size() != MAX_ENEMIES) {
            counter++;
            if (counter % SPAWN_RATE == 0) {
                randomizer();
                if (enemies.size() == MAX_ENEMIES) {
                    break;
                }
                break;
            }
        }

    }

    //MODIFIES: This
    //EFFECT: Randomly select where enemy will spawn
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
        bullets.removeAll(bulletsRemove);

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

    //MODIFY: this
    //EFFECT: Change value of isGameOver to true if player is dead, clear bullet and enemy
    // list if isGameOver is true
    public void gameOver() {
        if (player.getHealth() <= 0) {
            isGameOver = true;

        }

        if (isGameOver) {
            enemies.clear();
            bullets.clear();
        }

    }

    //EFFECT: Return player
    public Player getPlayer() {
        return player;
    }

    //EFFECT: Return bullet list
    public ArrayList<Bullets> getBullets() {
        return bullets;
    }

    //EFFECT: Return enemy list
    public ArrayList<Enemies> getEnemies() {
        return enemies;
    }

    //EFFECT: Return true if game is over, false if game is not over
    public boolean getisGameOver() {
        return isGameOver;
    }

    //EFFECT: Help test randomizer, search for the wanted enemy and returns it
    public Enemies randomTest(ArrayList<Enemies> a, Enemies target) {
        Enemies en = null;
        for (Enemies next: a) {
            if (next == target) {
                en = next;
            }
        }
        return en;
    }
}
