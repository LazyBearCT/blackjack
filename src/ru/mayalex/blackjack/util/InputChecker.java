package ru.mayalex.blackjack.util;

import java.io.IOException;

public class InputChecker {

    private static final Scanner in = new Scanner(System.in);

    public static boolean askPlayer(String text) {
        try {
            while (true) {
                System.out.print(text);
                char response = (char) System.in.read();
                if (response == 'y' || response == 'Y') {
                    return true;
                }
                if (response == 'n' || response == 'N') {
                    return false;
                }
                System.out.println("Please, try again");
            }
        } catch (IOException e) {
            try {
                System.in.close();
            } catch (IOException ex) {
                System.err.println("System.in can't closed!");
            }
            throw new RuntimeException("Input failed!");
        }
    }
}
