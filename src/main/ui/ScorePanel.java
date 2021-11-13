package ui;

import model.Frame;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private static final String ITEM_TXT = "Item picked up";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private Frame game;
    private JLabel itemLbl;


    public ScorePanel(Frame g) {
        game = g;
        setBackground(new Color(180, 180, 180));
        itemLbl = new JLabel(ITEM_TXT + game.getNumItemPicked());
        itemLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(itemLbl);
        add(Box.createHorizontalStrut(10));

    }

    public void update() {
        itemLbl.setText(ITEM_TXT + game.getNumItemPicked());
        repaint();
    }
}
