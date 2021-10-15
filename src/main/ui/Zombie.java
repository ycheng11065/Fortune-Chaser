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
        play();


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



    public void play() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Move Player: 1 - Up, 2 - Down, 3 - Right, 4 - Left, 5 - Shoot ");
        int n = sc.nextInt();
        if (n == 1) {
            game.movePlayer("UP");
        } else if (n == 2) {
            game.movePlayer("DOWN");
        } else if (n == 3) {
            game.movePlayer("RIGHT");
        } else if (n == 5) {
            game.movePlayer("LEFT");
        }

    }



    //Start game
    public static void main(String[] args) {
        new Zombie();



    }
}
