package ru.mayalex.blackjack.deck;

import ru.mayalex.blackjack.player.AbstractPlayer;

import java.util.Collections;

public class Deck extends Hand {

    public Deck() {
        generate();
    }

    public void generate() {
        clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                addCard(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void deal(Hand hand) {
        if (cards.isEmpty()) {
            System.out.println("Out of cards. Unable to deal.");
        } else {
            hand.addCard(cards.remove(cards.size() - 1));
        }
    }

    public void additionalCards(AbstractPlayer player) {
        System.out.println();
        while (!(player.isBusted()) && player.isHitting()) {
            deal(player);
            System.out.println(player);
            if (player.isBusted()) {
                player.bust();
            }
        }
    }
}
