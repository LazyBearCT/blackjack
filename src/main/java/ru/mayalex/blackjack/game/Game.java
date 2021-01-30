package ru.mayalex.blackjack.game;

import ru.mayalex.blackjack.deck.Deck;
import ru.mayalex.blackjack.player.AbstractPlayer;
import ru.mayalex.blackjack.player.Bot;
import ru.mayalex.blackjack.player.Dealer;
import ru.mayalex.blackjack.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private Dealer dealer;
    private List<AbstractPlayer> players;
    private int countBots;
    private int countActivePlayers;

    public Game(int countBots, List<String> names) {
        this.countBots = countBots;
        players = new ArrayList<>();
        for (int i = 1; i <= countBots; i++) {
            players.add(new Bot(i));
        }
        for (String name : names) {
            players.add(new Player(name));
        }
        dealer = new Dealer();
        countActivePlayers = countBots + names.size();
    }

    public void play() {
        deck = new Deck();
        deck.shuffle();
        for (AbstractPlayer player : players) {
            player.makeBet();
        }
        for (int i = 0; i < 2; i++) {
            for (AbstractPlayer player : players) {
                if (player.isActivePlayer()) {
                    deck.deal(player);
                }
            }
            deck.deal(dealer);
        }
        dealer.flipFirstCard();
        for (AbstractPlayer player : players) {
            System.out.println(player);
        }
        System.out.println(dealer);
        int count = 0;
        for (AbstractPlayer player : players) {
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
            for (AbstractPlayer player : players) {
                if (player.isActivePlayer()) {
                    if (!player.isBusted()) {
                        player.win();
                    } else {
                        player.bust();
                    }
                } else {
                    player.bankrupt();
                }
            }
        } else {
            int dealerTotal = dealer.getTotal();
            for (AbstractPlayer player : players) {
                if (player.isActivePlayer()) {
                    if (!player.isBusted()) {
                        int playerTotal = player.getTotal();
                        if (playerTotal > dealerTotal) {
                            player.win();
                        } else if (playerTotal < dealerTotal) {
                            player.lose();
                        } else {
                            player.draw();
                        }
                    } else {
                        player.bust();
                    }
                } else {
                    player.bankrupt();
                }
            }
        }
        System.out.println("\nPlayers: ");
        System.out.println(dealer);
        for (AbstractPlayer player : players) {
            System.out.println(player);
            if (player.getBalance() <= 0) {
                countActivePlayers--;
                player.deactivatePlayer();
            }
            player.clear();
        }
        dealer.clear();
        if (countActivePlayers == 1) {
            System.out.println("Game over!!!");
        }
    }
}
