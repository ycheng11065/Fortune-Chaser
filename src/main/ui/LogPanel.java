package ui;

import model.Event;
import model.EventLog;
import model.MainGame;

import javax.swing.*;
import java.awt.*;

/**
 * Frame showing all the fortune message collected
 * Reference: SpaceInvader
 */

public class LogPanel extends JFrame implements Panel {
    private static final int LABEL_WIDTH = 50;
    private static final int LABEL_HEIGHT = 50;

    private static final String LOG_TEXT = "Log: ";
    private MainGame game;

    //EFFECT: Create a frame showing all fortune message from collected cookies
    public LogPanel(MainGame frame) {
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ychen\\OneDrive\\Desktop\\Fortune.jpg");
        setIconImage(icon);
        game = frame;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setTitle("Log Panel");

        GridLayout g1 = new GridLayout();
        g1.setRows(game.getFoodScore() + game.getTreasureScore() + game.getGameCount() + 1);
        setLayout(g1);
        centerOnScreen();
    }

    //EFFECT: Create a label as header then turn all treasure fortune message within pocket into label
    public void update() {
        JLabel logLbl = new JLabel(LOG_TEXT);
        logLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        add(logLbl);
        if (game.getisGameOver()) {
            for (Event next: EventLog.getInstance()) {
                JLabel a = new JLabel(next.toString());
                a.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
                add(a);
            }
        }
        pack();
    }

    //EFFECT: Centers frame
    public void centerOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}

