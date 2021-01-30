package ru.mayalex.blackjack;

import ru.mayalex.blackjack.game.Game;
import ru.mayalex.blackjack.util.InputChecker;

import java.util.ArrayList;
import java.util.List;

public class Blackjack {

    public static void main(String[] args) {
        System.out.println("\t\tWelcome to Blackjack!\n");
        int countBots = 0;
        while (countBots < 1 || countBots > 3) {
            System.out.print("How many bots? (1 - 3): ");
            countBots = InputChecker.nextInt();
        }
        int countPlayers = 0;
        while (countPlayers < 1 || countPlayers > 3) {
            System.out.print("How many hunan players? (1 - 3): ");
            countPlayers = InputChecker.nextInt();
        }
        System.out.println("Please, enter player names.");
        List<String> names = new ArrayList<>();
        for (int i = 0; i < countPlayers; i++) {
            names.add(InputChecker.next());
        }
        Game game = new Game(countBots, names);
        do {
            game.play();
            System.out.println();
        } while (InputChecker.askPlayer("Do you want to play again? (y/n): "));
    }
}
