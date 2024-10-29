package ui;

import manager.*;
import model.*;
import model.MainGame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Draw the game by creating a panel
 * Reference: SpaceInvader
 */

public class GamePanel extends JPanel {
    public static int TRANSPARENCY = 160;
    public static Font customFont70;
    public static Font customFont40;
    public static Font customFont30;

    private static final String PAUSE = "Paused";
    private static final String OVER = "Game Over!";
    private static final String OVER2 = "You have unfortunately died!";
    private static final String WON = "Congratulations!";
    private static final String WON2 = "You have collected all the fortune cookies!";
    private static final String INSTRUCTION = "Press the following:";
    private static final String REPLAY = "R to replay";
    private static final String FORTUNE = "I to show fortune";
    private static final String SAVE = "O to save fortune messages";
    private static final String LOG = "L to view log";

    private World wr;
    private MainGame game;
    private ScorePanel sp;
    private DecimalFormat decimalFormat;

    //EFFECT: Set size and background color to panel as well as drawing the game
    public GamePanel(MainGame game) {
        wr = new World(game, "/maps/world1.txt");
        setBackground(new Color(139, 69, 19));
        setPreferredSize(new Dimension(MainGame.FRAMEWIDTH, MainGame.FRAMEHEIGHT));
        this.game = game;
        game.setWr(wr);
        sp = new ScorePanel(game);
        decimalFormat = new DecimalFormat("#0.000000");

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            customFont70 = Font.createFont(Font.TRUETYPE_FONT, is);
            customFont70 = customFont70.deriveFont(Font.PLAIN, 70);
            customFont40 = customFont70.deriveFont(Font.PLAIN, 40);
            customFont30 = customFont70.deriveFont(Font.PLAIN, 30);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (game.getisGameOver()) {
            gameOver(g);
        }

        if (game.getIsGameWon()) {
            gameWon(g);
        }
    }

    //MODIFIES: g
    //EFFECTS: Draw game onto g
    private void drawGame(Graphics g) {
        long drawStart = 0;
        drawStart = System.nanoTime();
        wr.render(g);
        drawTreasure(g);
        if (game.getPlayer().getWorldY() < game.getGuide().getWorldY()) {
            drawPlayer(g);
            drawGuide(g);
            System.out.println("behind");
        } else if (game.getPlayer().getWorldY() >= game.getGuide().getWorldY()){
            drawGuide(g);
            drawPlayer(g);
            System.out.println("front");
        }
//        drawFood(g);
//        drawPoison(g);
        sp.draw(g);
        double drawEnd = System.nanoTime();
        double passed = drawEnd - drawStart;
        double time = (passed / Math.pow(10, 9));
//        System.out.println("Draw Time: " + decimalFormat.format(time) + " seconds");
        if (game.getGameState() == game.PAUSESTATE) {
            drawPauseScreen(g);
        }

        if (game.getGameState() == game.DIALOGUESTATE) {
            drawDialogueScreen(g);
        }
    }

    public void resetTime() {
        sp.resetPlayTime();
    }

    public void drawDialogueScreen(Graphics g) {
        int x = game.TILE_SIZE * 2;
        int y = game.TILE_SIZE / 2;
        int width = game.FRAMEWIDTH - (game.TILE_SIZE * 4);
        int height = game.TILE_SIZE * 4;

        drawSubWindow(x, y, width, height, g);
    }

    public void drawSubWindow(int x, int y, int width, int height, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(new Color(0 , 0, 0, TRANSPARENCY));
        g.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    private void drawPauseScreen(Graphics g) {
        g.drawRect(game.FRAMEWIDTH / 2 - 5 * game.TILE_SIZE, game.FRAMEHEIGHT / 2 - 5 * game.TILE_SIZE
                , (5 * game.TILE_SIZE) * 2, (5 * game.TILE_SIZE) * 2);
        g.setColor(new Color(192, 192, 192, 230));
        g.fillRect(game.FRAMEWIDTH / 2 - 5 * game.TILE_SIZE, game.FRAMEHEIGHT / 2 - 5 * game.TILE_SIZE
                , (5 * game.TILE_SIZE) * 2, (5 * game.TILE_SIZE) * 2);
        g.setColor(new Color(0, 0, 0));
        g.setFont(customFont40);
        FontMetrics fm = g.getFontMetrics();
        centreString(PAUSE, g, fm, MainGame.FRAMEHEIGHT / 2 - 200);
    }


    //MODIFIES: g
    //EFFECTS: Draw player onto g
    private void drawPlayer(Graphics g) {
        game.getPlayer().currentAnimation();
        game.getPlayer().draw(g);
        g.setColor(Color.cyan);
//        g.drawRect(game.getScreenx(),game.getScreeny() , MainGame.TILE_SIZE, MainGame.TILE_SIZE);
//        g.drawRect(game.getPlayer().getWorldX() - MainGame.TILE_SIZE / 2,
//                game.getPlayer().getWorldY() - MainGame.TILE_SIZE / 2, MainGame.TILE_SIZE, MainGame.TILE_SIZE);
//        g.setColor(Color.cyan);
//        g.fillRect(game.getPlayer().getWorldX() - MainGame.TILE_SIZE / 2,
//                game.getPlayer().getWorldY() - MainGame.TILE_SIZE / 2, MainGame.TILE_SIZE, MainGame.TILE_SIZE);
    }

    private void drawGuide(Graphics g) {
        game.getGuide().getCurrentAnimation();
        game.getGuide().draw(g);
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
        ArrayList<Consumables> t = game.getObjs();
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i) != null) {
                t.get(i).draw(g, game);

            }
        }
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
        g.drawRect(game.FRAMEWIDTH / 2 - 5 * game.TILE_SIZE, game.FRAMEHEIGHT / 2 - 5 * game.TILE_SIZE
                , (5 * game.TILE_SIZE) * 2, (5 * game.TILE_SIZE) * 2);
        g.setColor(new Color(192, 192, 192, 230));
        g.fillRect(game.FRAMEWIDTH / 2 - 5 * game.TILE_SIZE, game.FRAMEHEIGHT / 2 - 5 * game.TILE_SIZE
                , (5 * game.TILE_SIZE) * 2, (5 * game.TILE_SIZE) * 2);
        g.setColor(new Color(0, 0, 0));
        g.setFont(customFont40);
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, MainGame.FRAMEHEIGHT / 2 - 200);
        centreString(OVER2, g, fm, MainGame.FRAMEHEIGHT / 2 - 150);
        centreString(INSTRUCTION, g, fm, MainGame.FRAMEHEIGHT / 2);
        centreString(REPLAY, g, fm, MainGame.FRAMEHEIGHT / 2 + 50);
        centreString(FORTUNE, g, fm, MainGame.FRAMEHEIGHT / 2 + 100);
        centreString(SAVE, g, fm, MainGame.FRAMEHEIGHT / 2 + 150);
        centreString(LOG, g, fm, MainGame.FRAMEHEIGHT / 2 + 200);
    }

    private void gameWon(Graphics g) {
        g.drawRect(game.FRAMEWIDTH / 2 - 5 * game.TILE_SIZE, game.FRAMEHEIGHT / 2 - 5 * game.TILE_SIZE
        , (5 * game.TILE_SIZE) * 2, (5 * game.TILE_SIZE) * 2);
        g.setColor(new Color(192, 192, 192, 230));
        g.fillRect(game.FRAMEWIDTH / 2 - 5 * game.TILE_SIZE, game.FRAMEHEIGHT / 2 - 5 * game.TILE_SIZE
                , (5 * game.TILE_SIZE) * 2, (5 * game.TILE_SIZE) * 2);
        g.setColor(new Color(0, 0, 0));
        g.setFont(customFont40);
        FontMetrics fm = g.getFontMetrics();
        centreString(WON, g, fm, MainGame.FRAMEHEIGHT / 2 - 200);
        centreString(WON2, g, fm, MainGame.FRAMEHEIGHT / 2 - 150);
        centreString(INSTRUCTION, g, fm, MainGame.FRAMEHEIGHT / 2);
        centreString(REPLAY, g, fm, MainGame.FRAMEHEIGHT / 2 + 50);
        centreString(FORTUNE, g, fm, MainGame.FRAMEHEIGHT / 2 + 100);
        centreString(SAVE, g, fm, MainGame.FRAMEHEIGHT / 2 + 150);
        centreString(LOG, g, fm, MainGame.FRAMEHEIGHT / 2 + 200);
    }

    //MODIFIES: g
    //EFFECTS: Centers the string horizontally onto g at verical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int ypos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (MainGame.FRAMEWIDTH - width) / 2, ypos);
    }
}
