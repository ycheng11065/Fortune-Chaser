package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Consumables {
    public static final int SIZEX = 20;
    public static final int SIZEY = 20;
    public static final Color COLOUR = new Color(0, 255, 0);
    protected BufferedImage image;
    protected int worldX;
    protected int worldY;
    protected boolean collision = false;
    protected String name;

    public Consumables(int x, int y) {
        worldX = x;
        worldY = y;
    }

    //EFFECTS: Return true if player hit food
    public boolean hit(Player p) {
        Rectangle foodRectangle = new Rectangle(worldX - SIZEX / 2, worldY - SIZEY / 2,
                SIZEX, SIZEY);
        Rectangle playerRectangle = new Rectangle(p.getWorldX() - MainGame.TILE_SIZE / 2,
                p.getWorldY() - MainGame.TILE_SIZE / 2, MainGame.TILE_SIZE, MainGame.TILE_SIZE);
        return foodRectangle.intersects(playerRectangle);

    }

    //EFFECTS: Return x coordinate
    public int getX() {
        return worldX;
    }

    //EFFECTS: Return y coordinate
    public int getY() {
        return worldY;
    }

    public void draw(Graphics g, MainGame game) {
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

    public String getName() {
        return name;
    }
}
