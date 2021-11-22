package ui;

import model.Frame;
import model.Treasure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel displaying fortunes similarly to Fortune Panel
 * Reference: JsonSerialization demo, SpaceInvader
 */

public class LoadPanel extends JFrame {
    private static final int LABEL_WIDTH = 50;
    private static final int LABEL_HEIGHT = 50;

    private static final String FORTUNE_TXT = "Your fortune: ";
    private List<Treasure> treasures;

    //EFFECTS: Create a frame showing all fortune message from file
    public LoadPanel(List<Treasure> treasures) {

        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ychen\\OneDrive\\Desktop\\Fortune.jpg");
        setIconImage(icon);

        this.treasures = treasures;

//        JButton button1 = new JButton("Delete Fortune");
//        button1.addActionListener(this);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setTitle("Fortune Panel");

//        add(button1);
        GridLayout g1 = new GridLayout();
        g1.setRows(treasures.size() + 1);
        setLayout(g1);
        update();
        centerOnScreen();
    }

    //EFFECTS: Create a label as header then turn all treasure fortune message into label
    public void update() {
        JLabel fortuneLbl = new JLabel(FORTUNE_TXT);
        fortuneLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        add(fortuneLbl);
        for (Treasure next: treasures) {
            JLabel a = new JLabel(next.getMsg());
            a.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            add(a);
        }
        pack();
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        List<Treasure> zero =  new ArrayList<>();
//        new LoadPanel(zero);
//        this.dispose();
//
//    }

    //EFFECTS: Centers frame
    private void centerOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}

