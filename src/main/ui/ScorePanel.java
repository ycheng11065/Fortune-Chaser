package ui;

import model.MainGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;

/**
 * Create a panel showing scores
 * Reference: SpaceInvader
 */

public class ScorePanel extends JPanel {
    private MainGame game;
    private int cookieCounter;
    private int bootCounter;
    private double playTime;
    private DecimalFormat decimalFormat;

    private BufferedImage transparentCookie;

    //EFFECTS: sets the background colour and draws initial labels where it is constantly updated by game
    public ScorePanel(MainGame g) {
        game = g;
        setBackground(new Color(180, 180, 180));
        decimalFormat = new DecimalFormat("#0.00");

        try {
            transparentCookie = ImageIO.read(getClass().getResourceAsStream(("/sprites/transparentCookie.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: update number of food and treasure collected as well as show player health remaining
    public void draw(Graphics g) {
        g.drawRect(20, 16, game.TILE_SIZE * 4, game.TILE_SIZE);
        g.setColor(new Color(192, 192, 192, 200));
        g.fillRect(20, 16, game.TILE_SIZE * 4, game.TILE_SIZE);

        g.setFont(GamePanel.customFont70);
        g.setColor(Color.WHITE);
        g.drawString(" X " + game.getTreasureScore(), game.TILE_SIZE / 2 + 60, game.TILE_SIZE + 7);
        g.drawImage(transparentCookie, game.TILE_SIZE / 2, game.TILE_SIZE / 2 - 15
                , game.TILE_SIZE, game.TILE_SIZE, null);


        g.drawRect(game.TILE_SIZE * 11 + 40, 16, game.TILE_SIZE * 4 + 2, game.TILE_SIZE);
        g.setColor(new Color(192, 192, 192, 200));
        g.fillRect(game.TILE_SIZE * 11 + 40, 16, game.TILE_SIZE * 4 + 2, game.TILE_SIZE);
        g.setColor(Color.WHITE);
        g.drawString("Time: " + decimalFormat.format(playTime), game.TILE_SIZE * 11 + 40, 68);

        if (!game.getisGameOver() && !game.getIsGameWon() && game.getGameState() != game.PAUSESTATE) {
            playTime += (double) 1/60;

        }

        if (game.getMessageCode() == 1) {
            g.setFont(GamePanel.customFont30);
            g.drawString("Picked up fortune cookie!", game.TILE_SIZE / 2, game.TILE_SIZE * 5);
            cookieCounter++;
            if (cookieCounter > 120) {
                cookieCounter = 0;
                game.setMessageCode(0);
            }
        } else if (game.getMessageCode() == 2) {
            g.setFont(GamePanel.customFont30);
            g.drawString("Picked up boots!", game.TILE_SIZE / 2, game.TILE_SIZE * 5);
            bootCounter++;
            if (bootCounter > 120) {
                bootCounter = 0;
                game.setMessageCode(0);
            }
        }
    }
}
