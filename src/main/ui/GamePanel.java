package ui;

import model.*;
import model.Frame;

import javax.swing.*;
import java.awt.*;

/**
 * Draw the game by creating a panel
 * Reference: SpaceInvader
 */

public class GamePanel extends JPanel {

    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private static final String FORTUNE = "I to show fortune";
    private static final String SAVE = "O to save fortune messages";
    private static final String LOG = "L to view log";

    private Frame game;

    //EFFECT: Set size and background color to panel as well as drawing the game
    public GamePanel(Frame g) {
        setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT));
        setBackground(Color.GRAY);
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (game.getisGameOver()) {
            gameOver(g);
        }
    }

    //MODIFIES: g
    //EFFECTS: Draw game onto g
    private void drawGame(Graphics g) {
        drawPlayer(g);
        drawFood(g);
        drawTreasure(g);
        drawPoison(g);
    }

    //MODIFIES: g
    //EFFECTS: Draw player onto g
    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        Color savedCol = g.getColor();
        g.setColor(Player.COLOUR);
        g.fillRect(p.getXcoord() - Player.SIZEX / 2, p.getYcoord() - Player.SIZEY / 2, Player.SIZEX, Player.SIZEY);
        g.setColor(savedCol);
    }

    //MODIFIES: G
    //EFFECTS: Draw food onto g
    private void drawFood(Graphics g) {
        Food f = game.getFood();
        Color savedCol = g.getColor();
        g.setColor(Food.COLOUR);
        g.fillOval(f.getX() - Food.SIZEX / 2, f.getY() - Food.SIZEY / 2, Food.SIZEX, Food.SIZEY);
        g.setColor(savedCol);

    }

    //MODIFIES: G
    //EFFECTS: Draw treasure onto g
    private void drawTreasure(Graphics g) {
        Treasure t = game.getTreasure();
        Color savedCol = g.getColor();
        g.setColor(Treasure.COLOUR);
        g.fillOval(t.getX() - Treasure.SIZEX / 2, t.getY() - Treasure.SIZEY / 2, Treasure.SIZEX, Treasure.SIZEY);
        g.setColor(savedCol);

    }

    //MODIFIES: G
    //EFFECTS: Draw poison onto g
    private void drawPoison(Graphics g) {
        Poison p = game.getPoison();
        Color savedCol = g.getColor();
        g.setColor(Poison.COLOUR);
        g.fillOval(p.getX() - Poison.SIZEX / 2, p.getY() - Poison.SIZEY / 2, Poison.SIZEX, Poison.SIZEY);
        g.setColor(savedCol);

    }

    //MODIFIES: g
    //EFFECTS: Draw "game over" as well as showing instruction for replay and saving fortune message
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 40));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, Frame.HEIGHT / 2 - 100);
        centreString(REPLAY, g, fm, Frame.HEIGHT / 2 - 50);
        centreString(FORTUNE, g, fm, Frame.HEIGHT / 2);
        centreString(SAVE, g, fm, Frame.HEIGHT / 2 + 50);
        centreString(LOG, g, fm, Frame.HEIGHT / 2 + 100);
        g.setColor(saved);
    }

    //MODIFIES: g
    //EFFECTS: Centers the string horizontally onto g at verical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int ypos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (Frame.WIDTH - width) / 2, ypos);
    }

}
