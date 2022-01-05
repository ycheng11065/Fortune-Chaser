package model;

import manager.Tile;
import ui.GamePanel;
import ui.Main;

import java.awt.*;

/**
 * Represents a player with a position a coordinate x y and health
 * Reference: Space Invader
 */

public class Player {

    public static final int HEALTH = 200;
    public static final int SIZEX = 480;
    public static final int SIZEY = 480;

    private int health;
    private int worldX;
    private int worldY;

    private Rectangle solidArea;
    private Boolean collisonOn = false;
    private int speed = 5;

    private String dir = "still";

    private MainGame game;

    //EFFECTS: Creates the player at the desired x y coordinate with health
    public Player(int x, int y, int h, MainGame game) {
        this.worldX = x;
        this.worldY = y;
        this.health = h;
        this.game = game;

        solidArea = new Rectangle(8, 30, 32, 25); // Player collision rectangle
        // 8 16
    }

    //MODIFIES: this
    //EFFECTS: Move player horizontally in vel direction
    public void moveX(int vel) {
        if (vel > 0) {
            int tx = (int) (worldX + vel + solidArea.x + solidArea.width) / MainGame.TILE_SIZE;
            if (!collisionWithTile(tx, (int) (worldY + solidArea.y) / MainGame.TILE_SIZE) &&
                    !collisionWithTile(tx, (int) (worldY + solidArea.y + solidArea.height) / MainGame.TILE_SIZE)) {
                worldX += vel;
            }  else {
                worldX = tx * MainGame.TILE_SIZE - solidArea.x - solidArea.width -1;
            }
        } else if (vel < 0) {
            int tx = (int) (worldX + vel + solidArea.x) / MainGame.TILE_SIZE;

            if (!collisionWithTile(tx, (int) (worldY + solidArea.y) / MainGame.TILE_SIZE) &&
                    !collisionWithTile(tx, (int) (worldY + solidArea.y + solidArea.height) / MainGame.TILE_SIZE)) {
                worldX += vel;
            } else {
                worldX = tx * MainGame.TILE_SIZE + MainGame.TILE_SIZE - solidArea.x;
            }
        }
//        xboundary();
    }

    //MODIFIES: this
    //EFFECTS: Move player vertically in vel direction
    public void moveY(int vel) {
        if (vel > 0) {
            int ty = (int) (worldY + vel + solidArea.y + solidArea.height) / MainGame.TILE_SIZE;

            if (!collisionWithTile((int) (worldX + solidArea.x) / MainGame.TILE_SIZE, ty) &&
                    !collisionWithTile((int) (worldX + solidArea.x + solidArea.width) / MainGame.TILE_SIZE, ty)) {
                worldY += vel;
            }  else {
                worldY = ty * MainGame.TILE_SIZE - solidArea.y - solidArea.height - 1;
            }
        } else if (vel < 0) {
            int ty = (int) (worldY + vel + solidArea.y) / MainGame.TILE_SIZE;

            if (!collisionWithTile((int) (worldX + solidArea.x) / MainGame.TILE_SIZE, ty) &&
                    !collisionWithTile((int) (worldX + solidArea.x + solidArea.width) / MainGame.TILE_SIZE, ty)) {
                worldY += vel;
            }  else {
                worldY = ty * MainGame.TILE_SIZE + MainGame.TILE_SIZE - solidArea.y;
            }
        }
//        worldY += vel;
//        yboundary();
    }

    public boolean collisionWithTile(int x, int y) {
        int tile = game.getWr().getTileNum(x, y);
        return game.getWr().getTile(tile).getCollision();
    }

    //MODIFIES: this
    //EFFECT: Constrain player from leaving game boundary on x axis
    private void xboundary() {
        if (worldX < 10) {
            worldX = 10;
        } else if (worldX > MainGame.WORLD_WIDTH - 10) {
            worldX = MainGame.WORLD_HEIGHT - 10;
        }

    }

    //MODIFIES: this
    //EFFECTS: Constrain player from leaving game boundary on y axis
    public void yboundary() {
        if (worldY < 10) {
            worldY = 10;
        } else if (worldY > MainGame.WORLD_HEIGHT - 10) {
            worldY = MainGame.WORLD_HEIGHT - 10;
        }

    }

    //MODIFIES: this
    //EFFECTS: Apply damage to player health
    public void healthDmg(int d) {
        health = health - d;
    }

    //EFFECTS: Return x coordinate
    public int getWorldX() {
        return worldX;
    }

    //EFFECTS: Return y coordinate
    public int getWorldY() {
        return worldY;
    }

    //EFFECTS: Return player health
    public int getHealth() {
        return health;
    }

    //MODIFIES: this
    //EFFECTS: Put health back to full
    public void eat() {
        health = HEALTH;
    }

    //MODIFIES: this
    //EFFECTS: Move player to new x and y position
    public void movePlayer(int x, int y) {
        worldX = x;
        worldY = y;
    }

    public void setCollisonOn(Boolean collisonOn) {
        this.collisonOn = collisonOn;
    }

    public Boolean getCollisonOn() {
        return collisonOn;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
