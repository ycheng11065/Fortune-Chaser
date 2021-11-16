package ui;

import model.Frame;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private static final String ITEM_TXT = "Item picked up";
    private static final String HEALTH_TXT = "Health Remaining";
    private static final String TREASURE_TXT = "Treasure picked up";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private Frame game;
    private JLabel itemLbl;
    private JLabel healthLbl;
    private JLabel treasureLbl;


    public ScorePanel(Frame g) {
        game = g;
        setBackground(new Color(180, 180, 180));
        itemLbl = new JLabel(ITEM_TXT + game.getFoodScore());
        itemLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        healthLbl = new JLabel(HEALTH_TXT + game.getPlayer().getHealth());
        healthLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        treasureLbl = new JLabel(TREASURE_TXT + game.getTreasureScore());
        treasureLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(treasureLbl);
        add(Box.createHorizontalStrut(10));
        add(itemLbl);
        add(Box.createHorizontalStrut(10));
        add(healthLbl);
    }

    public void update() {
        treasureLbl.setText(TREASURE_TXT + " " + game.getTreasureScore());
        itemLbl.setText(ITEM_TXT + " " + game.getFoodScore());
        healthLbl.setText(HEALTH_TXT + " " + game.getPlayer().getHealth());
        repaint();
    }
}
