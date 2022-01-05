package ui;

import model.Event;
import model.EventLog;
import model.MainGame;
import model.GameFile;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Menu panel consisting of two buttons, one for starting game, the other for load gui
 * Reference: JsonSerialization demo, SpaceInvader
 */

public class Menu extends JFrame implements ActionListener {
    JButton button1;
    JButton button2;
    JButton button3;

    private static final String JSON_STORE = "./data/gameFile.json";
    private GameFile gameFile;
    private JsonReader jsonReader;
    private JLabel gameTitle;

    //EFFECT: Creates a panel containing two buttons
    public Menu() {

        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ychen\\OneDrive\\Desktop\\Fortune.jpg");
        setIconImage(icon);

        gameTitle = new JLabel("Fortune Chaser");

        gameFile = new GameFile("My gamefile");
        jsonReader = new JsonReader(JSON_STORE);

        button1 = new JButton("Start game");
        button1.addActionListener(this);

        button2 = new JButton("Load Fortune");
        button2.addActionListener(this);

        button3 = new JButton("Print Log");
        button3.addActionListener(this);

        setPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        closed();
        setTitle("Fortune Chaser");
        setPreferredSize(new Dimension(MainGame.FRAMEWIDTH, MainGame.FRAMEHEIGHT));
        pack();
        setVisible(true);
        setLocationRelativeTo((Component) null);
    }

    //EFFECT: Connect the two gui button to action events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            try {
                new FortuneChaser(gameFile, JSON_STORE);
                setVisible(false);

            } catch (FileNotFoundException ex) {
                System.out.println("Unable to run application: file not found");
            }
        } else if (e.getSource() == button2) {
            loadSaveFile();

        }
    }

    // MODIFIES: this
    // EFFECTS: loads gamefile from file
    private void loadSaveFile() {
        try {
            gameFile = jsonReader.read();
            System.out.println("Loaded " + gameFile.getName() + " from " + JSON_STORE);
            new LoadPanel(gameFile.getTreasures());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void closed() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event next: EventLog.getInstance()) {
                    System.out.println(next);
                }
                System.exit(0);
            }
        });
    }

    public void setPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(gameTitle);
        panel.add(button1);
        panel.add(button2);
        add(panel, BorderLayout.CENTER);
    }

}
