package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.deck.Hand;

public abstract class AbstractPlayer {

    protected String name;
    protected Hand hand = new Hand();

    protected AbstractPlayer(String name) {
        this.name = name;
    }

    public boolean isBusted() {
        return hand.getTotal() > 21;
    }

    public void bust() {
        System.out.println(name + " busts.");
    }

    public abstract boolean isHitting();
}
