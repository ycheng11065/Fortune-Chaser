package ui;

import manager.*;
import model.*;
import model.MainGame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Draw the game by creating a panel
 * Reference: SpaceInvader
 */

public class GamePanel extends JPanel {
    static Font customFont70;
    static Font customFont40;
    static Font customFont30;

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

//    private Consumables[] objs = new Consumables[10];
//    private ObjectSetter objectSetter;

    //EFFECT: Set size and background color to panel as well as drawing the game
    public GamePanel(MainGame game) {
        wr = new World(game, "/maps/world1.txt");
        setBackground(new Color(139, 69, 19));
        setPreferredSize(new Dimension(MainGame.FRAMEWIDTH, MainGame.FRAMEHEIGHT));
        this.game = game;
        game.setWr(wr);
        sp = new ScorePanel(game);

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            customFont70 = Font.createFont(Font.TRUETYPE_FONT, is);
            customFont70 = customFont70.deriveFont(Font.PLAIN, 70);
            customFont40 = customFont70.deriveFont(Font.PLAIN, 40);
            customFont30 = customFont70.deriveFont(Font.PLAIN, 30);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
//        objectSetter = new ObjectSetter(this);
//        objectSetter.setObject();
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
        drawPlayer(g);
        drawFood(g);
        drawPoison(g);
        sp.draw(g);
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        System.out.println("Draw Time: " + passed);
    }


    //MODIFIES: g
    //EFFECTS: Draw player onto g
    private void drawPlayer(Graphics g) {
        g.drawImage(game.getCurrentAnimation(), game.getScreenx(), game.getScreeny(), game.TILE_SIZE, game.TILE_SIZE, null);
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
