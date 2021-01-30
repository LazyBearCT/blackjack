package ru.mayalex.blackjack.deck;

import ru.mayalex.blackjack.player.AbstractPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<>();

    public Deck() {
        generate();
    }

    public void generate() {
        deck.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void deal(AbstractPlayer player) {
        if (deck.isEmpty()) {
            System.out.println("Out of cards. Unable to deal.");
        } else {
            player.addCard(deck.remove(deck.size() - 1));
        }
    }

    public void additionalCards(AbstractPlayer player) {
        System.out.println();
        System.out.println(player);
        while (!(player.isBusted()) && player.isHitting()) {
            deal(player);
            System.out.println(player);
            if (player.isBusted()) {
                player.bust();
            }
        }
    }
}
