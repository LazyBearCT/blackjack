package ru.mayalex.blackjack;

import ru.mayalex.blackjack.game.Game;
import ru.mayalex.blackjack.util.InputChecker;

import java.util.ArrayList;
import java.util.List;

public class Blackjack {

    public static void main(String[] args) {
        System.out.println("\t\tWelcome to Blackjack!\n");
        int countBots = InputChecker.getCount("bots");
        int countPlayers = InputChecker.getCount("human players");
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
