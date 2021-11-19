package ui;

import model.Frame;
import model.GameFile;
import model.Treasure;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import javax.swing.*;


/**
 * Create main window displaying Fortune Chaser
 * Reference: JsonSerialization demo, SpaceInvader
 */

public class FortuneChaser extends JFrame {

    private static final int Interval = 10;

    private Frame game;
    private GamePanel gp;
    private ScorePanel sp;

    private String jsonStore;
    private GameFile gameFile;
    private JsonWriter jsonWriter;

    //EFFECT: Set main window where game takes place
    public FortuneChaser(GameFile gameFile, String store) throws FileNotFoundException {
        super("FortuneChaser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        game =  new Frame();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        add(gp);
        add(sp, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centerOnScreen();
        setVisible(true);

        //JSON
        this.gameFile = gameFile;
        jsonStore = store;
        jsonWriter = new JsonWriter(jsonStore);

        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ychen\\OneDrive\\Desktop\\Fortune.jpg");
        setIconImage(icon);

        addCounter();
    }

    //EFFECT: Timer that updates game every Interval millisecond
    public void addCounter() {
        Timer t1 = new Timer(Interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                sp.update();
                gp.repaint();
            }
        });

        t1.start();
    }

    //EFFECTS: Centers frame
    private void centerOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
            if (e.getKeyCode() == KeyEvent.VK_O && game.getisGameOver() == true) {
                gameFile.clearJson();
                addPocket();
                saveGameFile();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            game.keyReleased(e.getKeyCode());
        }
    }

    // EFFECTS: Add treasure to GameFile
    private void addPocket() {
        for (Treasure next: game.getPocket().getPocket()) {
            gameFile.addTreasure(next);
        }
    }

    // EFFECTS: saves the GameFile to file
    private void saveGameFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameFile);
            jsonWriter.close();
            System.out.println("Saved " + gameFile.getName() + " to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }
    }



}
