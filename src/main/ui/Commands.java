package ui;

import model.Map;

import java.util.Scanner;

public class Commands {

    public String nextCommand() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        if (cmd.equals(null)) {
            return "";

        } else {
            return cmd;
        }
    }


}
