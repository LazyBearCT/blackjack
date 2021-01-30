package ru.mayalex.blackjack.deck;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    protected List<Card> cards = new ArrayList<>();
    private boolean alreadyCalculate = false;
    private int total = 0;

    public void clear() {
        cards.clear();
    }

    public void addCard(Card card) {
        alreadyCalculate = false;
        cards.add(card);
    }

    public int getTotal() {
        if (alreadyCalculate) {
            return total;
        }
        alreadyCalculate = true;
        if (cards.isEmpty() || cards.get(0).getValue() == 0) {
            return 0;
        }
        int score = 0;
        boolean isContainsAce = false;
        for (Card card : cards) {
            score += card.getValue();
            if (card.getRank() == Rank.ACE) {
                isContainsAce = true;
            }
        }
        if (isContainsAce && score <= 11) {
            score += 10;
        }
        total = score;
        return score;
    }
}
