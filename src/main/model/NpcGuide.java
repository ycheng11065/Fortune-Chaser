package model;

import manager.Animation;
import manager.ImageInventory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NpcGuide extends Entity {
    private int speed;
    private BufferedImage image;

    private final Animation anmStill;
    private final Animation anmDown;
    private final Animation anmUp;
    private final Animation anmLeft;
    private final Animation anmRight;

    private int actionLockCounter = 0;


    public NpcGuide(int h, int x, int y, MainGame game) {
        super(h, x, y, game);
        this.speed = 1;

        anmStill = new Animation(500, ImageInventory.getPlayerStill());
        anmDown = new Animation(500, ImageInventory.getPlayerUp());
        anmUp = new Animation(500, ImageInventory.getPlayerDown());
        anmLeft = new Animation(500, ImageInventory.getPlayerLeft());
        anmRight = new Animation(500, ImageInventory.getPlayerRight());
    }

    public void move() {

        actionLockCounter++;

        if (actionLockCounter == 90) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Random number 1 to 100;

            if (i <= 20) {
                velY = speed * -1;
            } else if (i > 20 && i <= 40) {
                velY = speed;
            } else if (i > 40 && i <= 60) {
                velX = speed * -1;
            } else if (i > 60 && i <= 80) {
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
        anmStill.tick();
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
            image = anmDown.getCurrentFrame();
        } else if (velY > 0) {
            image = anmUp.getCurrentFrame();
        } else if (velY == 0 && velX == 0) {
            image = anmStill.getCurrentFrame();
        }
    }
}
