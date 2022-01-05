package ui;

//import manager.Handler;
import manager.World;
import model.MainGame;
import model.GameFile;
import model.Treasure;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import javax.swing.*;


/**
 * Create main window displaying Fortune Chaser
 * Reference: JsonSerialization demo, SpaceInvader
 */

public class FortuneChaser extends JFrame {

    private static final int Interval = 10;

    private MainGame game;
    private GamePanel gp;
    private ScorePanel sp;
    private World wr;

    private String jsonStore;
    private GameFile gameFile;
    private JsonWriter jsonWriter;

    //EFFECT: Set main window where game takes place
    public FortuneChaser(GameFile gameFile, String store) throws FileNotFoundException {
        super("FortuneChaser");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(false);
        game =  new MainGame();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        add(gp);
        add(sp, BorderLayout.SOUTH);
        addKeyListener(new KeyHandler());
        pack();
        centerOnScreen();
        setVisible(true);
        closed();

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
                gp.repaint();
//                sp.repaint();
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

            if (e.getKeyCode() == KeyEvent.VK_O && (game.getisGameOver() || game.getIsGameWon())) {
                gameFile.clearJson();
                addPocket();
                saveGameFile();
            } else if (e.getKeyCode() == KeyEvent.VK_R && (game.getisGameOver() || game.getIsGameWon())) {
                game.start();
            } else if (e.getKeyCode() == KeyEvent.VK_I && (game.getisGameOver() || game.getIsGameWon())) {
                FortunePanel fp = new FortunePanel(game);
                fp.update();
            } else if (e.getKeyCode() == KeyEvent.VK_X) {
                System.exit(0);
            } else if (e.getKeyCode() == KeyEvent.VK_L) {
                LogPanel lg = new LogPanel(game);
                lg.update();
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

    public void closed() {
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
//                int amount = game.getObjs().size();
                new Menu();
                if (game.getSound().getClip() != null) {
                    game.stopSE();
                }
                game.stopMusic();
                game.setGameOver(true);
            }
        });
    }
}
