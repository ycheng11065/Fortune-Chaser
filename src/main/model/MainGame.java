package model;

import manager.Animation;
import manager.ImageInventory;
import manager.World;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents Fortune Rush game
 * Reference: Space Invader
 */

public class MainGame {
    public final static int ORIGINAL_TILE_SIZE = 16; // 16 x 16 tile
    public final static int SCALE = 4;

    public final static int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48 x 48 tile
    public final static int MAXSCREENCOL = 16;
    public final static int MAXSCREENROW = 12;
    public final static int FRAMEWIDTH = TILE_SIZE * MAXSCREENCOL;   // 768 pixels, orig: 800
    public final static int FRAMEHEIGHT = TILE_SIZE * MAXSCREENROW;   // 576 pixels, orig:  500

    // WORLD SETTINGS
    public final static int MAXWORLDCOL = 50;
    public final static int MAXWORLDROW = 50;
    public final static int WORLD_WIDTH = TILE_SIZE * MAXWORLDCOL;
    public final static int WORLD_HEIGHT = TILE_SIZE * MAXWORLDROW;

    private final int screenx; // Center of screen
    private final int screeny; // Center of screen

    public static final int HUNGER_DMG = 1;
    public static final Random RAND = new Random();

    public static final int PLAYSTATE = 1;
    public static final int PAUSESTATE = 2;

    private Player player;
    private Food food;
    private Treasure treasure;
    private Pocket pocket;
    private Poison poison;

    private String msg;
    private boolean isGameOver;
    private boolean isGameWon;
    private int velX = 0;
    private int velY = 0;
    private int foodScore;
    private int treasureScore;
    private ArrayList<String> fortune;
    private int gameCount;

    private Animation anmStill;
    private Animation anmDown;
    private Animation anmUp;
    private Animation anmLeft;
    private Animation anmRight;

    private CollisionChecker collisionChecker;

    private ArrayList<Consumables> objs = new ArrayList<Consumables>();
    private ObjectSetter objectSetter;
    private Consumables obj;

    private Sound sound;
    private Sound music;

    private World wr;

    private int messageCode;

    private int gameState;

    //EFFECT: start game
    public MainGame() {
        screenx = FRAMEWIDTH / 2 - TILE_SIZE / 2;
        screeny = FRAMEHEIGHT / 2 - TILE_SIZE / 2;
        ImageInventory.init();
        gameCount = 0;
        anmStill = new Animation(500, ImageInventory.getPlayerStill());
        anmDown = new Animation(500, ImageInventory.getPlayerUp());
        anmUp = new Animation(500, ImageInventory.getPlayerDown());
        anmLeft = new Animation(500, ImageInventory.getPlayerLeft());
        anmRight = new Animation(500, ImageInventory.getPlayerRight());
        sound = new Sound();
        music = new Sound();
        collisionChecker = new CollisionChecker(this);
        objectSetter = new ObjectSetter(this);
        start();
    }

    //MODIFY: this
    //EFFECT: Start game, spawn player, spawn treasure, spawn food, reset score, create pocket, create msg list, set msg
    public void start() {
        gameState = PLAYSTATE;
        EventLog.getInstance().logEvent(new Event("New game begun"));
        player = new Player(TILE_SIZE * 6, TILE_SIZE * 6, Player.HEALTH, this);
        pocket = new Pocket();
        fortune = new ArrayList<String>();
        food = spawnFood();
        poison = spawnPoison();
        objectSetter = new ObjectSetter(this);
        objectSetter.setObject();
        addMsg();
        isGameOver = false;
        isGameWon = false;
        foodScore = 0;
        treasureScore = 0;
        playMusic(0);
        gameCount++;
    }

    //MODIFY: this
    //EFFECTS: Update movement, check to spawn food, check to spawn treasure, check if game over, reduce player health,
    public void update() {
        if (!isGameOver && !isGameWon && gameState == PLAYSTATE) {
            player.moveX(velX);
            player.moveY(velY);
//            System.out.println(player.getWorldX());
//            System.out.println(player.getWorldY());
            updateFood();
            updateTreasure();
            updatePoison();
            animationTick();
            if (canPoison()) {
                poison = spawnPoison();
            }
//            if (player.getHealth() > 0) {
//                playerHunger();
//            }
            animationTick();
            gameWon();
            gameOver();

        } else if (gameState == PAUSESTATE) {

        }
    }

    public void updateFood() {
        if (canEat()) {
            player.eat();
            EventLog.getInstance().logEvent(new Event("Food eaten"));
            foodScore++;
            food = spawnFood();
        }
    }

    public void updateTreasure() {
        if (canPickUp()) {
            if (obj.getName() == "Treasure") {
                messageCode = 1;
                playSE(1);
                Treasure tr = (Treasure) obj;
                setMsg(tr);
                pocket.addTreasure(tr);
                EventLog.getInstance().logEvent(new Event("Fortune cookie collected"));
                treasureScore++;


            } else if (obj.getName() == "Boots") {
                messageCode = 2;
                playSE(2);
                player.setSpeed(player.getSpeed() * 3);
            }
            objs.remove(obj);
        }
    }

    public void updatePoison() {
        if (canPoison()) {
            EventLog.getInstance().logEvent(new Event("Poisoned"));
            pocket.clear();
        }
    }

    public void animationTick() {
        anmStill.tick();
        anmDown.tick();
        anmUp.tick();
        anmRight.tick();
        anmLeft.tick();
    }

    //MODIFIES: this
    //EFFECTS: Connect key press to specific function
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_P) {
            if (gameState == PLAYSTATE) {
                gameState = PAUSESTATE;
            } else if (gameState == PAUSESTATE) {
                gameState = PLAYSTATE;
            }
        }

        if (gameState != PAUSESTATE) {
            if (keyCode == KeyEvent.VK_W) {
                player.setDir("up");
                velY = -1 * player.getSpeed();
            } else if (keyCode == KeyEvent.VK_S) {
                player.setDir("down");
                velY = player.getSpeed();
            } else if (keyCode == KeyEvent.VK_A) {
                player.setDir("left");
                velX = -1 * player.getSpeed();
            } else if (keyCode == KeyEvent.VK_D) {
                player.setDir("right");
                velX = player.getSpeed();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: Releasing key stop player movement
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

    public BufferedImage getCurrentAnimation() {
        if (velX < 0) {
            return anmLeft.getCurrentFrame();
        } else if (velX > 0) {
            return anmRight.getCurrentFrame();
        } else if (velY < 0) {
            return anmDown.getCurrentFrame();
        } else if (velY > 0) {
            return anmUp.getCurrentFrame();
        } else {
            return anmStill.getCurrentFrame();
        }
    }

    //MODIFIES: this
    //EFFECTS: Reduce player health
    public void playerHunger() {
        player.healthDmg(HUNGER_DMG); //def : HUNGER_DMG
    }

    //MODIFIES: this
    //EFFECTS: Change value of isGameOver to true if player is dead
    public void gameOver() {
        if (player.getHealth() <= 0) {
            isGameOver = true;
            stopMusic();
        }
    }

    public void gameWon() {
        if (pocket.getPocket().size() == 3) {
            isGameWon = true;
            stopMusic();
        }
    }

    //EFFECTS: Spawn food to random location, if location is same than player then choose new location
    public Food spawnFood() {
        Random rand = new Random();
        int upperboundx = WORLD_WIDTH - 10;
        int upperboundy = WORLD_HEIGHT - 10;
        int lowerbound = 10;

        int xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
        int yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;

        return new Food(xrandom, yrandom);
    }

//    //EFFECTS: Spawn treasure to random location, if location is same than player then choose new location
//    public Treasure spawnTreasure() {
//        Random rand = new Random();
//        int upperboundx = WORLD_WIDTH - 10;
//        int upperboundy = WORLD_HEIGHT - 10;
//        int lowerbound = 10;
//
//        int xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
//        int yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;
//
//        return new Treasure(xrandom, yrandom);
//    }

    //EFFECTS: Spawn Poison to random location, if location is same than player then choose new location
    public Poison spawnPoison() {
        Random rand = new Random();
        int upperboundx = WORLD_WIDTH - 10;
        int upperboundy = WORLD_HEIGHT - 10;
        int lowerbound = 10;

        int xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
        int yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;

        return new Poison(xrandom, yrandom);
    }

    //EFFECTS: return true if player touch food
    public Boolean canEat() {
        return food.hit(player);
    }

    public Boolean canPoison() {
        return poison.hit(player);
    }

    //EFFECTS: return true if player touch treasure
    public Boolean canPickUp() {
        Boolean isHit = false;
        for (Consumables next: objs) {
            if (next.hit(player)) {
                isHit = true;
                obj = next;
            }
        }
        return isHit;
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void stopSE() {
        sound.stop();
    }

    //MODIFIES: this
    //EFFECTS: Add default msg to fortune
    public void addMsg() {
        fortune.add("A beautiful, smart, and loving person will be coming into your life.");
        fortune.add("A dubious friend may be an enemy in camouflage.");
        fortune.add("A faithful friend is a strong defense.");
        fortune.add("A feather in the hand is better than a bird in the air.");
        fortune.add("A fresh start will put you on your way.");
        fortune.add("A friend asks only for your time not your money.");
        fortune.add("A friend is a present you give yourself.");
        fortune.add("A gambler not only will lose what he has, but also will lose what he doesn’t have.");
        fortune.add("A golden egg of opportunity falls into your lap this month.");
        fortune.add("A good friendship is often more important than a passionate romance.");
        fortune.add("A good time to finish up old tasks.");
        fortune.add("A hunch is creativity trying to tell you something.");
        addMsg2();
    }

    //MODIFIES: this
    //EFFECTS: Add default msg to fortune
    public void addMsg2() {
        fortune.add("A lifetime friend shall soon be made.");
        fortune.add("A lifetime of happiness lies ahead of you.");
        fortune.add("A light heart carries you through all the hard times.");
        fortune.add("Savor your freedom – it is precious.");
        fortune.add("Say hello to others. You will have a happier day.");
        fortune.add("Self-knowledge is a life long process.");
        fortune.add("Share your joys and sorrows with your family.");
        fortune.add("Sift through your past to get a better idea of the present.");
        fortune.add("Sloth makes all things difficult; industry all easy.");
        fortune.add("Small confidences mark the onset of a friendship.");
        fortune.add("Success is going from failure to failure without loss of enthusiasm.");
        fortune.add("Swimming is easy. Stay floating is hard.");
        addMsg3();
    }

    //MODIFIES: this
    //EFFECTS: Add default msg to fortune
    public void addMsg3() {
        fortune.add("The first man gets the oyster, the second man gets the shell.");
        fortune.add("The greatest achievement in life is to stand up again after falling.");
        fortune.add("The harder you work, the luckier you get.");
        fortune.add("The night life is for you.");
        fortune.add("The one that recognizes the illusion does not act as if it is real.");
        fortune.add("The only people who never fail are those who never try.");
        fortune.add("The person who will not stand for something will fall for anything.");
        fortune.add("The philosophy of one century is the common sense of the next.");
        fortune.add("The secret to good friends is no secret to you.");
        fortune.add("The small courtesies sweeten life, the greater ennoble it.");
        fortune.add("The sure way to predict the future is to invent it.");
        fortune.add("The truly generous share, even with the undeserving.");
        fortune.add("The value lies not within any particular thing, but in the desire placed on that thing.");
        fortune.add("There is a time for caution, but not for fear.");
        fortune.add("Those who care will make the effort.");
        fortune.add("Time and patience are called for many surprises await you!.");
        fortune.add("Welcome change.");
        fortune.add("What’s that in your eye? Oh…it’s a sparkle.");
    }

    //MODIFIES: this
    //EFFECTS: set velx with new value
    public void setVelX(int x) {
        velX = x;
    }

    //MODIFIES: this
    //EFFECTS: set vely with new value
    public void setVelY(int y) {
        velY = y;
    }

    //MODIFIES: this
    //EFFECTS: put random msg within treasure, if fortune is out of msg then return out of fortune
    public void setMsg(Treasure yes) {
        Random r = new Random();
        if (fortune.size() == 0) {
            treasure.addMsg("Out of fortune");
        } else {
            msg = fortune.get(r.nextInt(fortune.size()));
            yes.addMsg(msg);
            fortune.remove(msg);
        }
    }

    //MODIFIES: this
    //EFFECTS: create new food at x y
    public void setFood(int x, int y) {
        food = new Food(x, y);
    }

    //MODIFIES: this
    //EFFECTS: create new treasure at x y
    public void setTreasure(int x, int y) {
        treasure = new Treasure(x, y);
    }

    //EFFECTS: Return player
    public Player getPlayer() {
        return player;
    }

    //EFFECTS: Return treasure
    public Treasure getTreasure() {
        return treasure;
    }

    //EFFECTS: Return true if game is over, false if game is not over
    public boolean getisGameOver() {
        return isGameOver;
    }

    //EFFECTS: return food score
    public int getFoodScore() {
        return foodScore;
    }

    //EFFECTS: return treasure score
    public int getTreasureScore() {
        return treasureScore;
    }

    //EFFECTS: return pocket
    public Pocket getPocket() {
        return pocket;
    }

    //EFFECTS: return velY
    public int getVelY() {
        return velY;
    }

    //EFFECTS: return fortune
    public ArrayList<String> getFortune() {
        return fortune;
    }

    //EFFECTS: return food
    public Food getFood() {
        return food;
    }

    //EFFECTS: return poison
    public Poison getPoison() {
        return poison;
    }

    //EFFECTS: return velY
    public int getVelX() {
        return velX;
    }

    //EFFECTS: return game count
    public int getGameCount() {
        return gameCount;
    }

    public int getScreenx() {
        return screenx;
    }

    public int getScreeny() {
        return screeny;
    }

    public void setWr(World wr) {
        this.wr = wr;
    }

    public World getWr() {
        return wr;
    }

    public ArrayList<Consumables> getObjs() {
        return objs;
    }

    public void setObjs(ArrayList<Consumables> objs) {
        this.objs = objs;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Sound getSound() {
        return sound;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public boolean getIsGameWon() {
        return isGameWon;
    }

    public int getGameState() {
        return gameState;
    }
}
