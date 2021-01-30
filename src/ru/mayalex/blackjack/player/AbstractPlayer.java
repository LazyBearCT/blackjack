package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.deck.Card;
import ru.mayalex.blackjack.deck.Hand;

public abstract class AbstractPlayer extends Hand {

    protected String name;

    protected AbstractPlayer(String name) {
        this.name = name;
    }

    public boolean isBusted() {
        return getTotal() > 21;
    }

    public void bust() {
        System.out.println(name + " busts.");
    }

    public void win() {
        System.out.println(name + " wins.");
    }

    public void lose() {
        System.out.println(name + " loses.");
    }

    public void draw() {
        System.out.println(name + "draw");
    }

    @Override
    public String toString() {
        System.out.print(name + ":\t");
        if (cards.isEmpty()) {
            return "<empty>";
        }
        StringBuilder string = new StringBuilder();
        for (Card card : cards) {
            string.append(card + "\t");
        }
        int total = getTotal();
        if (total != 0) {
            string.append("(" + total + ")");
        }
        return string.toString();
    }

    public abstract boolean isHitting();
}
