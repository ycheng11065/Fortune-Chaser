package ui;

import model.Frame;
import model.Treasure;

import javax.swing.*;
import java.awt.*;

public class FortunePanel extends JFrame {
    private static final int LABEL_WIDTH = 50;
    private static final int LABEL_HEIGHT = 50;

    private static final String FORTUNE_TXT = "Your fortune: ";
    private Frame game;

    public FortunePanel(Frame frame) {
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ychen\\OneDrive\\Desktop\\Fortune.jpg");
        setIconImage(icon);
        game = frame;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setTitle("Fortune Panel");

        GridLayout g1 = new GridLayout();
        g1.setRows(game.getPocket().getPocket().size() + 1);
        setLayout(g1);
        centerOnScreen();
    }

    public void update() {
        JLabel fortuneLbl = new JLabel(FORTUNE_TXT);
        fortuneLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        add(fortuneLbl);
        if (game.getisGameOver()) {
            for (Treasure next: game.getPocket().getPocket()) {
                JLabel a = new JLabel(next.getMsg());
                a.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
                add(a);
            }
        }
        pack();
    }

    private void centerOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}
