package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public static final int HEALTH = 200;
    protected int health;
    protected int worldX;
    protected int worldY;
    protected int velX = 0;
    protected int velY = 0;
    protected Rectangle solidArea;
    protected MainGame game;
    protected Boolean collisonOn = false;
    protected int speed;
    protected BufferedImage image;

    public Entity(int h, int x, int y, MainGame game) {
        this.health = h;
        this.worldX = x;
        this.worldY = y;
        solidArea = new Rectangle(12, 30, 30, 25); // Player collision rectangle
        this.game = game;
    }

    //MODIFIES: this
    //EFFECTS: Move player horizontally in vel direction
    public void moveX() {
        if (velX > 0) {
            int tx = (int) (worldX + velX + solidArea.x + solidArea.width) / MainGame.TILE_SIZE;
            if (!collisionWithTile(tx, (int) (worldY + solidArea.y) / MainGame.TILE_SIZE) &&
                    !collisionWithTile(tx, (int) (worldY + solidArea.y + solidArea.height) / MainGame.TILE_SIZE)) {
                worldX += velX;
            }  else {
                worldX = tx * MainGame.TILE_SIZE - solidArea.x - solidArea.width -1;
            }
        } else if (velX < 0) {
            int tx = (int) (worldX + velX + solidArea.x) / MainGame.TILE_SIZE;

            if (!collisionWithTile(tx, (int) (worldY + solidArea.y) / MainGame.TILE_SIZE) &&
                    !collisionWithTile(tx, (int) (worldY + solidArea.y + solidArea.height) / MainGame.TILE_SIZE)) {
                worldX += velX;
            } else {
                worldX = tx * MainGame.TILE_SIZE + MainGame.TILE_SIZE - solidArea.x;
            }
        }
        xboundary();
    }

    //MODIFIES: this
    //EFFECTS: Move player vertically in vel direction
    public void moveY() {
        if (velY > 0) {
            int ty = (int) (worldY + velY + solidArea.y + solidArea.height) / MainGame.TILE_SIZE;

            if (!collisionWithTile((int) (worldX + solidArea.x) / MainGame.TILE_SIZE, ty) &&
                    !collisionWithTile((int) (worldX + solidArea.x + solidArea.width) / MainGame.TILE_SIZE, ty)) {
                worldY += velY;
            }  else {
                worldY = ty * MainGame.TILE_SIZE - solidArea.y - solidArea.height - 1;
            }
        } else if (velY < 0) {
            int ty = (int) (worldY + velY + solidArea.y) / MainGame.TILE_SIZE;

            if (!collisionWithTile((int) (worldX + solidArea.x) / MainGame.TILE_SIZE, ty) &&
                    !collisionWithTile((int) (worldX + solidArea.x + solidArea.width) / MainGame.TILE_SIZE, ty)) {
                worldY += velY;
            }  else {
                worldY = ty * MainGame.TILE_SIZE + MainGame.TILE_SIZE - solidArea.y;
            }
        }
        yboundary();
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


    public boolean collisionWithTile(int x, int y) {
        int tile = game.getWr().getTileNum(x, y);
        return game.getWr().getTile(tile).getCollision();
    }

    public void draw(Graphics g) {

        g.drawImage(image, game.getScreenx(), game.getScreeny(), game.TILE_SIZE, game.TILE_SIZE, null);
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

    public void setCollisonOn(Boolean collisonOn) {
        this.collisonOn = collisonOn;
    }

    public Boolean getCollisonOn() {
        return collisonOn;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
