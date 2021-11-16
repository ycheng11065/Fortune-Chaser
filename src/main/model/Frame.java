package model;

import ui.FortunePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// SOURCE: SpaceInvaderBase

public class Frame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    public static final int HUNGER_DMG = 1;
    public static final Random RAND = new Random();

    private Player player;
    private Food food;
    private Treasure treasure;
    private Pocket pocket;

    private String msg;
    private boolean isGameOver;
    private int velX = 0;
    private int velY = 0;
    private int foodScore;
    private int treasureScore;
//    private String[] fortune =  {"A beautiful, smart, and loving person will be coming into your life.",
//            "A dubious friend may be an enemy in camouflage.",
//            "A faithful friend is a strong defense.",
//            "A feather in the hand is better than a bird in the air.",
//            "A fresh start will put you on your way.",
//            "A friend asks only for your time not your money.",
//            "A friend is a present you give yourself."};
    private ArrayList<String> fortune;

    //EFFECT: Create empty list of bullets and enemies, spawns the player
    public Frame() {
        start();
    }

    //MODIFY: this
    //EFFECT: Start game, clear screen, spawn player
    public void start() {
        player = new Player(WIDTH / 2, HEIGHT / 2, Player.HEALTH, "UP");
        pocket = new Pocket();
        fortune = new ArrayList<String>();
        food = spawnFood();
        treasure = spawnTreasure();
        addMsg();
        isGameOver = false;
        foodScore = 0;
        treasureScore = 0;
    }

    //MODIFY: this
    //EFFECT: Update movement, Bullets and enemies
    public void update() {
        if (!isGameOver) {
            player.moveX(velX);
            player.moveY(velY);
            System.out.println(player.getXcoord());
            System.out.println(player.getYcoord());
            if (canEat()) {
                player.eat();
                foodScore++;
                food = spawnFood();
            }
            if (canPickUp()) {
                setMsg();
                pocket.addTreasure(treasure);
                treasureScore++;
                treasure = spawnTreasure();
            }
            if (player.getHealth() > 0) {
                playerHunger();
            }
            gameOver();
        }

    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            velY = -1 * player.RATEY;
        } else if (keyCode == KeyEvent.VK_S) {
            velY = player.RATEY;
        } else if (keyCode == KeyEvent.VK_A) {
            velX = -1 * player.RATEX;
        } else if (keyCode == KeyEvent.VK_D) {
            velX = player.RATEX;
        } else if (keyCode == KeyEvent.VK_R && isGameOver) {
            start();
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        } else if (keyCode == KeyEvent.VK_I && isGameOver) {
            FortunePanel fp = new FortunePanel(this);
            fp.update();
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

    public void playerHunger() {
        player.healthDmg(HUNGER_DMG);
    }

    //MODIFY: this
    //EFFECT: Change value of isGameOver to true if player is dead, clear bullet and enemy
    // list if isGameOver is true
    public void gameOver() {
        if (player.getHealth() <= 0) {
            isGameOver = true;

        }
    }

    public Food getFood() {
        return food;
    }

    //EFFECT: Return player
    public Player getPlayer() {
        return player;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    //EFFECT: Return true if game is over, false if game is not over
    public boolean getisGameOver() {
        return isGameOver;
    }

    public int getFoodScore() {
        return foodScore;
    }

    public int getTreasureScore() {
        return treasureScore;
    }


    public Food spawnFood() {
        Random rand = new Random();
        int upperboundx = WIDTH - 10;
        int upperboundy = HEIGHT - 10;
        int lowerbound = 10;

        int xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
        int yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;

        while (xrandom == player.getXcoord() && yrandom == player.getYcoord()) {
            xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
            yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;
        }

        return new Food(xrandom, yrandom);
    }

    public Treasure spawnTreasure() {
        Random rand = new Random();
        int upperboundx = WIDTH - 10;
        int upperboundy = HEIGHT - 10;
        int lowerbound = 10;

        int xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
        int yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;

        while ((xrandom == player.getXcoord() && yrandom == player.getYcoord())
                || (xrandom == food.getX() && yrandom == food.getY())) {
            xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
            yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;
        }

        return new Treasure(xrandom, yrandom);
    }

    public Boolean canEat() {
        return food.hit(player);
    }

    public Boolean canPickUp() {
        return treasure.hit(player);
    }

    public void addMsg() {
        fortune.add("A beautiful, smart, and loving person will be coming into your life.");
        fortune.add("A dubious friend may be an enemy in camouflage.");
        fortune.add("A faithful friend is a strong defense.");
        fortune.add("A feather in the hand is better than a bird in the air.");
        fortune.add("A fresh start will put you on your way.");
        fortune.add("A friend asks only for your time not your money.");
        fortune.add("A friend is a present you give yourself.");
    }

    public void setMsg() {
        Random r = new Random();
        if (fortune.size() == 0) {
            treasure.addMsg("Out of fortune");
        } else {
            msg = fortune.get(r.nextInt(fortune.size()));
            treasure.addMsg(msg);
            fortune.remove(msg);
        }
    }

    public Pocket getPocket() {
        return pocket;
    }

}
