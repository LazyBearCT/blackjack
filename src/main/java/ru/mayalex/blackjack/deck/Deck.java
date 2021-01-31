package ru.mayalex.blackjack.deck;

import ru.mayalex.blackjack.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck;

    public Deck() {
        generate();
    }

    public void generate() {
        deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void deal(Player player, int index) {
        if (deck.isEmpty()) {
            System.out.println("Out of cards. Unable to deal.");
        } else {
            player.addCard(index, deck.remove(deck.size() - 1));
        }
    }

    public void additionalCards(Player player) {
        System.out.println();
        System.out.println(player);
        for (int i = 0; i < player.getCountHands(); i++) {
            while (!(player.isBusted(i)) && player.isHitting(i)) {
                deal(player, i);
                System.out.println(player);
                if (player.isBusted(i)) {
                    player.bust(i);
                }
            }
        }
    }
}
