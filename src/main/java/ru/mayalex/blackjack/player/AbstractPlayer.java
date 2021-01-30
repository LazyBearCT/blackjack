package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.Blackjack;
import ru.mayalex.blackjack.deck.Card;
import ru.mayalex.blackjack.deck.Hand;

public abstract class AbstractPlayer {

    protected static final int MIN_BET = 1;
    protected static final int MAX_BET = 20;

    protected String name;
    protected Hand hand = new Hand();
    protected int balance;
    protected int bet;
    private boolean isActive = true;

    protected AbstractPlayer(String name) {
        this(name, 200);
    }

    protected AbstractPlayer(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isActivePlayer() {
        return isActive;
    }

    public void deactivatePlayer() {
        isActive = false;
    }

    public boolean isBusted() {
        return getTotal() > Blackjack.WIN_TOTAL;
    }

    public void bust() {
        System.out.println(name + " busts.");
    }

    public void win() {
        System.out.println(name + " wins.");
        if (isBlackjack()) {
            balance += 3 * bet;
        } else {
            balance += 2 * bet;
        }
    }

    public void bankrupt() {
        System.out.println(name + " is bankrupt");
        balance = 0;
    }

    public void lose() {
        System.out.println(name + " loses.");
    }

    public void draw() {
        System.out.println(name + " draw");
        balance += bet;
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
        if (isActivePlayer()) {
            string.append(hand);
            int total = getTotal();
            if (total != 0) {
                string.append("(" + total + ")\t");
            }
            string.append(balance + "$");
        } else {
            string.append("bankrupt");
        }
        return string.toString();
    }

    public abstract boolean isHitting();

    public abstract void makeBet();
}
