package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.Blackjack;
import ru.mayalex.blackjack.deck.Card;
import ru.mayalex.blackjack.deck.Hand;

public abstract class AbstractPlayer {

    protected String name;
    protected Hand hand = new Hand();

    protected AbstractPlayer(String name) {
        this.name = name;
    }

    public boolean isBusted() {
        return getTotal() > Blackjack.WIN_TOTAL;
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
        System.out.println(name + " draw");
    }

    public boolean isBlackjack() {
        return hand.size() == 2 && getTotal() == Blackjack.WIN_TOTAL;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getTotal() {
        return hand.getTotal();
    }

    public void clear() {
        hand.clear();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(name + ":\t");
        string.append(hand);
        int total = getTotal();
        if (total != 0) {
            string.append("(" + total + ")");
        }
        return string.toString();
    }

    public abstract boolean isHitting();
}
