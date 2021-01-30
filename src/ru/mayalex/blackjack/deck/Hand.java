package ru.mayalex.blackjack.deck;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards = new ArrayList<>();

    public void clear() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotal() {
        if (cards.isEmpty() || cards.get(0).getValue() == 0) {
            return 0;
        }
        int total = 0;
        boolean isContainsAce = false;
        for (Card card : cards) {
            total += card.getValue();
            if (card.getRank() == Rank.ACE) {
                isContainsAce = true;
            }
        }
        if (isContainsAce && total <= 11) {
            total += 10;
        }
        return total;
    }
}
