package model;

import manager.Animation;
import manager.ImageInventory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NpcGuide extends Entity {
    private int speed;
    private BufferedImage image;

    private final Animation anmStillDown;
    private final Animation anmStillUp;
    private final Animation anmStillRight;
    private final Animation anmStillLeft;

    private final Animation anmDown;
    private final Animation anmUp;
    private final Animation anmLeft;
    private final Animation anmRight;

    private int actionLockCounter = 0;


    public NpcGuide(int h, int x, int y, MainGame game) {
        super(h, x, y, game);
        this.speed = 1;

        anmStillDown = new Animation(500, ImageInventory.getNpcGuide_down_still());
        anmStillUp = new Animation(500, ImageInventory.getNpcGuide_up_still());
        anmStillRight = new Animation(500, ImageInventory.getNpcGuide_right_still());
        anmStillLeft = new Animation(500, ImageInventory.getNpcGuide_left_still());

        anmUp = new Animation(500, ImageInventory.getNpcGuide_up());
        anmDown = new Animation(500, ImageInventory.getNpcGuide_down());
        anmLeft = new Animation(500, ImageInventory.getNpcGuide_left());
        anmRight = new Animation(500, ImageInventory.getNpcGuide_right());

        image = anmStillDown.getCurrentFrame();
    }

    public void move() {

        actionLockCounter++;

        if (actionLockCounter == 90) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Random number 1 to 100;

            if (i <= 20) {
                direction = "up";
                velY = speed * -1;
            } else if (i > 20 && i <= 40) {
                direction = "down";
                velY = speed;
            } else if (i > 40 && i <= 60) {
                direction = "right";
                velX = speed * -1;
            } else if (i > 60 && i <= 80) {
                direction = "left";
                velX = speed;
            } else if (i > 80) {
                velX = 0;
                velY = 0;
            }
            actionLockCounter = 0;
        }
    }

    public boolean hit(Entity e) {
        Rectangle entity1 = new Rectangle(e.getWorldX() - MainGame.TILE_SIZE / 2,
                e.getWorldY() - MainGame.TILE_SIZE / 2, MainGame.TILE_SIZE, MainGame.TILE_SIZE);;
        Rectangle entity2 = new Rectangle(e.getWorldX() - MainGame.TILE_SIZE / 2,
                e.getWorldY() - MainGame.TILE_SIZE / 2, MainGame.TILE_SIZE, MainGame.TILE_SIZE);
        return entity1.intersects(entity2);

    }

    public void draw(Graphics g) {
        int screenX = worldX - game.getPlayer().getWorldX() + game.getScreenx();
        int screenY = worldY - game.getPlayer().getWorldY() + game.getScreeny();

        // Create boundary within player screen
        if (worldX + game.TILE_SIZE > game.getPlayer().getWorldX() - game.getScreenx() &&
                worldX - game.TILE_SIZE < game.getPlayer().getWorldX() + game.getScreenx() &&
                worldY + game.TILE_SIZE > game.getPlayer().getWorldY() - game.getScreeny() &&
                worldY - game.TILE_SIZE < game.getPlayer().getWorldY() + game.getScreeny()) {

            g.drawImage(image, screenX
                    , screenY, game.TILE_SIZE, game.TILE_SIZE, null);
        }
    }

    public void animationTick() {
        anmStillDown.tick();
        anmStillRight.tick();
        anmStillLeft.tick();
        anmStillUp.tick();

        anmDown.tick();
        anmUp.tick();
        anmRight.tick();
        anmLeft.tick();
    }

    public void getCurrentAnimation() {
        if (velX < 0) {
            image = anmLeft.getCurrentFrame();
        } else if (velX > 0) {
            image = anmRight.getCurrentFrame();
        } else if (velY < 0) {
            image = anmUp.getCurrentFrame();
        } else if (velY > 0) {
            image = anmDown.getCurrentFrame();
        } else if (velY == 0 && velX == 0) {
            if (direction == "down") {
                image = anmStillDown.getCurrentFrame();
            } else if (direction == "up") {
                image = anmStillUp.getCurrentFrame();
            } else if (direction == "right") {
                image = anmStillRight.getCurrentFrame();
            } else if (direction == "left") {
                image = anmStillLeft.getCurrentFrame();
            }
        }
    }
}
