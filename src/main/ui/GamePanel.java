package ui;

import model.Frame;
import model.Item;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private Frame game;

    public  GamePanel(Frame g) {
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
        drawItem(g);
    }

    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        Color savedCol = g.getColor();
        g.setColor(Player.COLOUR);
        g.fillRect(p.getXcoord() - Player.SIZEX / 2, Player.Y_POS - Player.SIZEY / 2, Player.SIZEX, Player.SIZEY);
        g.setColor(savedCol);
    }

    private void drawItem(Graphics g) {

    }

    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, Frame.HEIGHT / 2);
        centreString(REPLAY, g, fm, Frame.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    private void centreString(String str, Graphics g, FontMetrics fm, int ypos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (Frame.WIDTH - width) / 2, ypos);
    }

}
