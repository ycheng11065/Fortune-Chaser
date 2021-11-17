package ui;

import model.Frame;
import model.GameFile;
import model.Treasure;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;


//SOURCE: SpaceInvaderBase

public class FortuneChaser extends JFrame {

    private static final int Interval = 10;

    private Frame game;
    private GamePanel gp;
    private ScorePanel sp;

    private String jsonStore;
    private GameFile gameFile;
    private JsonWriter jsonWriter;

    //EFFECT: Create new game
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

    private void centerOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
            if (e.getKeyCode() == KeyEvent.VK_O && game.getisGameOver() == true) {
                addPocket();
                saveGameFile();
            }
        }


        @Override
        public void keyReleased(KeyEvent e) {
            game.keyReleased(e.getKeyCode());
        }
    }

    private void addPocket() {
        gameFile.addTreasure(game.getPocket());
    }

    // EFFECTS: saves the workroom to file
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
