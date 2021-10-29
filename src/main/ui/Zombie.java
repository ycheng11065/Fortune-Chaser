package ui;

import model.Map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.*;
import java.util.Scanner;

//SOURCE: SpaceInvaderBase

public class Zombie {

    private static final int Interval = 10;

    private Map game;

    //EFFECT: Create new game
    public Zombie() {
        game =  new Map();
        addCounter();
    }

    public void play(String n) {
        game.cmdReceived(n);
    }

    public Map getMap() {
        return game;
    }


    //EFFECT: Timer that updates game every Interval millisecond
    public void addCounter() {
        Timer t1 = new Timer(Interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
            }
        });

        t1.start();

    }
}
