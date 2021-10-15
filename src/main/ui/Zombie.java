package ui;

import model.Map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Zombie {

    private static final int Interval = 10;

    private Map game;


    public Zombie() {
        game =  new Map();
        addCounter();

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



    public static void main(String[] args) {
        new Zombie();

    }
}
