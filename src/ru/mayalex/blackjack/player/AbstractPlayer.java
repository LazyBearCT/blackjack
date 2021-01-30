package ru.mayalex.blackjack.player;

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
        System.out.println("draw");
    }

    public abstract boolean isHitting();
}
