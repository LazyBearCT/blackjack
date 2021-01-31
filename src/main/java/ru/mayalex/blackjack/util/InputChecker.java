package ru.mayalex.blackjack.util;

public class InputChecker {

    private static final Scanner in = new Scanner(System.in, true);

    public static int getCount(int max, String text) {
        return getCount(1, max, text);
    }

    public static int getCount(int min, int max, String text) {
        int count = 0;
        System.out.print(text);
        while (count < min || count > max) {
            count = nextInt(1, text);
        }
        return count;
    }

    private static int nextInt(int maxCount, String message) {
        return in.nextIntLine(maxCount, message).get(0);
    }

    public static String next() {
        return in.next();
    }

    public static boolean askPlayer(String text) {
        while (true) {
            System.out.print(text);
            String string = in.next();
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
    }
}
