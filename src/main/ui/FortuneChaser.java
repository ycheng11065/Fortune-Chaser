package ui;

import model.Frame;
import model.Treasure;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;


//SOURCE: SpaceInvaderBase

public class FortuneChaser extends JFrame {

    private static final int Interval = 10;

    private Frame game;
    private GamePanel gp;
    private ScorePanel sp;
    private FortunePanel fp;

    //EFFECT: Create new game
    public FortuneChaser() {
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

//    //EFFECTS: Forward player command to next method
//    public void play(String n) {
//        game.playerControl(n);
//    }

    //EFFECTS: Return map
    public Frame getMap() {
        return game;
    }


    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            game.keyReleased(e.getKeyCode());
        }
    }



}
