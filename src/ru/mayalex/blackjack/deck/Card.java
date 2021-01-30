package ru.mayalex.blackjack.deck;

public class Card {

    private Rank rank;
    private Suit suit;
    private boolean isFaceUp;

    public Card(Rank rank, Suit suit) {
        this(rank, suit, true);
    }

    public Card(Rank rank, Suit suit, boolean isFaceUp) {
        this.rank = rank;
        this.suit = suit;
        this.isFaceUp = isFaceUp;
    }

    public int getValue() {
        int value = 0;
        if (isFaceUp) {
            value = rank.getValue();
            if (value > 10) {
                value = 10;
            }
        }
        return value;
    }

    public void flip() {
        isFaceUp = !isFaceUp;
    }
}
