package ui;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Create a loading screen lasting five seconds before loading menu panel
 */

public class Main {

    public static void main(String[] args) throws MalformedURLException {
//        JWindow window = new JWindow();
//        window.getContentPane().add(
//                new JLabel("", new ImageIcon(
//                        new URL("https://wokbox.ca/wp-content/uploads/2017/01/thankyou-cracking-cookie-01.gif")),
//                        SwingConstants.CENTER));
//        window.setBounds(600, 300, 800, 500);
//
//        window.setVisible(true);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        window.setVisible(false);
//        window.setLocationRelativeTo((Component) null);

        new Menu();

    }
}