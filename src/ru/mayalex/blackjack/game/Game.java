package ru.mayalex.blackjack.game;

import ru.mayalex.blackjack.deck.Deck;
import ru.mayalex.blackjack.player.AbstractPlayer;
import ru.mayalex.blackjack.player.Dealer;
import ru.mayalex.blackjack.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private Dealer dealer;
    private List<AbstractPlayer> players;

    public Game(List<String> names) {
        players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        dealer = new Dealer();
        deck = new Deck();
        deck.shuffle();
    }

    public void play() {
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
        for (AbstractPlayer player : players) {
            deck.additionalCards(player);
        }
        dealer.flipFirstCard();
        System.out.println();
        deck.additionalCards(dealer);
        if (dealer.isBusted()) {
            for (AbstractPlayer player : players) {
                if (!player.isBusted()) {
                    player.win();
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
                }
            }
        }
        for (AbstractPlayer player : players) {
            player.clear();
        }
        dealer.clear();
    }
}
