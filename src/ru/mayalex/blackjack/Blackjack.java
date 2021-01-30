package ru.mayalex.blackjack;

import ru.mayalex.blackjack.game.Game;
import ru.mayalex.blackjack.util.InputChecker;

import java.util.ArrayList;
import java.util.List;

public class Blackjack {

    public static void main(String[] args) {
        System.out.println("\t\tWelcome to Blackjack!\n");
        int countPlayers = 0;
        while (countPlayers < 1 || countPlayers > 7) {
            System.out.print("How many players? (y/n): ");
            countPlayers = InputChecker.nextInt();
        }
        List<String> names = new ArrayList<>();
        for (int i = 0; i < countPlayers; i++) {
            names.add(InputChecker.next());
        }
        Game game = new Game(names);
        do {
            game.play();
        } while (InputChecker.askPlayer("\nDo you want to play again? (y/n): "));
    }
}
