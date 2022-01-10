package model;

import manager.Animation;
import manager.ImageInventory;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents a player with a position a coordinate x y and health
 * Reference: Space Invader
 */

public class Player extends Entity {
    public static final int ANIMSPEED = 100;

    private final Animation anmStill;
    private final Animation anmDown;
    private final Animation anmUp;
    private final Animation anmLeft;
    private final Animation anmRight;

    //EFFECTS: Creates the player at the desired x y coordinate with health
    public Player(int x, int y, int h, MainGame game) {
        super(h, x, y, game);
        this.speed = 5;

        anmStill = new Animation(ANIMSPEED, ImageInventory.getPlayerStill());
        anmDown = new Animation(ANIMSPEED, ImageInventory.getPlayerUp());
        anmUp = new Animation(ANIMSPEED, ImageInventory.getPlayerDown());
        anmLeft = new Animation(ANIMSPEED, ImageInventory.getPlayerLeft());
        anmRight = new Animation(ANIMSPEED, ImageInventory.getPlayerRight());
    }


    public boolean hit(Entity e) {
        Rectangle entity1 = new Rectangle(getWorldX() - MainGame.TILE_SIZE / 2,
                getWorldY() - MainGame.TILE_SIZE / 2, MainGame.TILE_SIZE, MainGame.TILE_SIZE);;
        Rectangle entity2 = new Rectangle(e.getWorldX() - MainGame.TILE_SIZE / 2,
                e.getWorldY() - MainGame.TILE_SIZE / 2, MainGame.TILE_SIZE, MainGame.TILE_SIZE);
        return entity1.intersects(entity2);
    }

    //MODIFIES: this
    //EFFECTS: Put health back to full
    public void eat() {
        health = HEALTH;
    }

    public void animationTick() {
        anmStill.tick();
        anmDown.tick();
        anmUp.tick();
        anmRight.tick();
        anmLeft.tick();
    }

    public void currentAnimation() {
        if (velX < 0) {
            image = anmLeft.getCurrentFrame();
        } else if (velX > 0) {
            image = anmRight.getCurrentFrame();
        } else if (velY < 0) {
            image = anmDown.getCurrentFrame();
        } else if (velY > 0) {
            image = anmUp.getCurrentFrame();
        } else {
            image = anmStill.getCurrentFrame();
        }
    }

}
