package ui;

import model.Food;
import model.Frame;
import model.Player;
import model.Treasure;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private static final String FORTUNE = "I to show fortune";
    private Frame game;

    public GamePanel(Frame g) {
        setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT));
        setBackground(Color.GRAY);
        this.game = g;
    }

    @Override
    protected  void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (game.getisGameOver()) {
            gameOver(g);
        }
    }

    private void drawGame(Graphics g) {
        drawPlayer(g);
        drawFood(g);
        drawTreasure(g);
    }

    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        Color savedCol = g.getColor();
        g.setColor(Player.COLOUR);
        g.fillRect(p.getXcoord() - Player.SIZEX / 2, p.getYcoord() - Player.SIZEY / 2, Player.SIZEX, Player.SIZEY);
        g.setColor(savedCol);
    }

    private void drawFood(Graphics g) {
        Food f = game.getFood();
        Color savedCol = g.getColor();
        g.setColor(Food.COLOUR);
        g.fillOval(f.getX() - Food.SIZEX / 2, f.getY() - Food.SIZEY / 2, Food.SIZEX, Food.SIZEY);
        g.setColor(savedCol);

    }

    private void drawTreasure(Graphics g) {
        Treasure t = game.getTreasure();
        Color savedCol = g.getColor();
        g.setColor(Treasure.COLOUR);
        g.fillOval(t.getX() - Treasure.SIZEX / 2, t.getY() - Treasure.SIZEY / 2, Treasure.SIZEX, Treasure.SIZEY);
        g.setColor(savedCol);

    }

    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 40));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, Frame.HEIGHT / 2 - 100);
        centreString(REPLAY, g, fm, Frame.HEIGHT / 2 - 50);
        centreString(FORTUNE, g, fm, Frame.HEIGHT / 2);
        g.setColor(saved);
    }

    private void centreString(String str, Graphics g, FontMetrics fm, int ypos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (Frame.WIDTH - width) / 2, ypos);
    }

}
