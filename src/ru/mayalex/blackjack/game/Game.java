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
    }

    public void play() {
        deck = new Deck();
        deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (AbstractPlayer player : players) {
                deck.deal(player);
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
            deck.additionalCards(player);
            count++;
        }
        dealer.flipFirstCard();
        deck.additionalCards(dealer);
        System.out.println();
        if (dealer.isBusted()) {
            for (AbstractPlayer player : players) {
                if (!player.isBusted()) {
                    player.win();
                } else {
                    player.bust();
                }
            }
        } else {
            int dealerTotal = dealer.getTotal();
            for (AbstractPlayer player : players) {
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
            }
        }
        for (AbstractPlayer player : players) {
            player.clear();
        }
        dealer.clear();
    }
}
