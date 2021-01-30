package ru.mayalex.blackjack.util;

import java.io.IOException;
import java.util.function.IntPredicate;

public class InputChecker {

    private static final Scanner in = new Scanner(System.in);

    public static int nextInt() {
        try {
            return in.nextInt();
        } catch (IOException e) {
            try {
                System.in.close();
            } catch (IOException ex) {
                System.err.println("System.in can't closed!");
            }
            throw new RuntimeException("Input failed!");
        }
    }

    public static String next() {
        try {
            return in.nextToken();
        } catch (IOException e) {
            try {
                System.in.close();
            } catch (IOException ex) {
                System.err.println("System.in can't closed!");
            }
            throw new RuntimeException("Input failed!");
        }
    }

    public static boolean askPlayer(String text) {
        try {
            while (true) {
                System.out.print(text);
                String string = in.nextToken();
                if (string.length() == 1) {
                    char response = Character.toLowerCase(string.charAt(0));
                    if (response == 'y') {
                        return true;
                    }
                    if (response == 'n') {
                        return false;
                    }
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
