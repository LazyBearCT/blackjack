package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.deck.Card;
import ru.mayalex.blackjack.deck.Hand;

public abstract class AbstractPlayer extends Hand {

    protected String name;

    protected AbstractPlayer(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        addCard(card);
    }

    public boolean isBusted() {
        return getTotal() > 21;
    }

    public void bust() {
        System.out.println(name + " busts.");
    }

    public abstract boolean isHitting();
}
