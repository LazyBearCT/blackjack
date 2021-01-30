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

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public void addCard(Card card) {
        alreadyCalculate = false;
        cards.add(card);
    }

    public int getTotal() {
        if (cards.isEmpty() || cards.get(0).getValue() == 0) {
            return 0;
        }
        if (alreadyCalculate) {
            return total;
        }
        alreadyCalculate = true;
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

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        if (cards.isEmpty()) {
            string.append("<empty>");
        } else {
            for (Card card : cards) {
                string.append(card + "\t");
            }
        }
        return string.toString();
    }
}
