package ru.mayalex.blackjack.game;

import ru.mayalex.blackjack.deck.Deck;
import ru.mayalex.blackjack.player.Player;
import ru.mayalex.blackjack.player.Bot;
import ru.mayalex.blackjack.player.Dealer;
import ru.mayalex.blackjack.player.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private Dealer dealer;
    private List<Player> players;
    private int countBots;
    private int countActivePlayers;

    public Game(int countBots, List<String> names) {
        this.countBots = countBots;
        players = new ArrayList<>();
        for (int i = 1; i <= countBots; i++) {
            players.add(new Bot(i));
        }
        for (String name : names) {
            players.add(new HumanPlayer(name));
        }
        dealer = new Dealer();
        countActivePlayers = countBots + names.size();
    }

    public void play() {
        deck = new Deck();
        deck.shuffle();
        for (Player player : players) {
            player.makeBet();
        }
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                if (player.isActivePlayer()) {
                    for (int j = 0; j < player.getCountHands(); j++) {
                        deck.deal(player, j);
                    }
                }
            }
            deck.deal(dealer, 0);
        }
        dealer.flipFirstCard();
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println(dealer);
        int count = 0;
        for (Player player : players) {
            if (count == countBots) {
                System.out.println();
                System.out.print(dealer);
            }
            if (player.isActivePlayer()) {
                deck.additionalCards(player);
            }
            count++;
        }
        dealer.flipFirstCard();
        deck.additionalCards(dealer);
        System.out.println();
        if (dealer.isBusted()) {
            for (Player player : players) {
                if (player.isActivePlayer()) {
                    for (int i = 0; i < player.getCountHands(); i++) {
                        if (!player.isBusted(i)) {
                            player.win(i);
                        } else {
                            player.bust(i);
                        }
                    }
                } else {
                    player.bankrupt();
                }
            }
        } else {
            int dealerTotal = dealer.getTotal();
            for (Player player : players) {
                if (player.isActivePlayer()) {
                    for (int i = 0; i < player.getCountHands(); i++) {
                        if (!player.isBusted(i)) {
                            int playerTotal = player.getTotal(i);
                            if (playerTotal > dealerTotal) {
                                player.win(i);
                            } else if (playerTotal < dealerTotal) {
                                player.lose(i);
                            } else {
                                player.draw(i);
                            }
                        } else {
                            player.bust(i);
                        }
                    }
                } else {
                    player.bankrupt();
                }
            }
        }
        System.out.println("\nPlayers: ");
        System.out.println(dealer);
        for (Player player : players) {
            System.out.println(player);
            player.clear();
        }
        dealer.clear();
    }
}
