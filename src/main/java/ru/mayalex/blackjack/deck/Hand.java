package ru.mayalex.blackjack.deck;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    protected List<Card> cards;
    private boolean alreadyCalculate = false;
    private int total = 0;
    private int bet;
    private boolean isLose;
    private boolean isLimit;

    public Hand(int bet) {
        this(new ArrayList<>(), bet);
    }

    private Hand(List<Card> cards, int bet) {
        this(cards, bet, false, false);
    }

    private Hand(List<Card> cards, int bet, boolean isLose, boolean isLimit) {
        this.cards = cards;
        this.bet = bet;
        this.isLose = isLose;
        this.isLimit = isLimit;
    }

    public int getBet() {
        return bet;
    }

    public boolean isLose() {
        return isLose;
    }

    public boolean isLimit() {
        return isLimit;
    }

    public int lose() {
        isLose = true;
        for (int i = 2; i <= bet; i++) {
            if (bet % i == 0) {
                return bet / i;
            }
        }
        return bet;
    }

    public Hand doubleBet() {
        return new Hand(cards, bet * 2, false, true);
    }

    public void clear() {
        cards.clear();
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Hand split() {
        if (cards.size() != 2) {
            throw new RuntimeException();
        }
        List<Card> cards1 = new ArrayList<>();
        cards1.add(cards.remove(cards.size() - 1));
        return new Hand(cards1, bet);
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
