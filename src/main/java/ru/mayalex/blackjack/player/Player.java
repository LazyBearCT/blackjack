package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.Blackjack;
import ru.mayalex.blackjack.deck.Card;
import ru.mayalex.blackjack.deck.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {

    protected static final int MIN_BET = 1;
    protected static final int MAX_BET = 20;

    protected String name;
    protected List<Hand> hands = new ArrayList<>();
    protected int balance;
    protected boolean isActive = true;

    protected Player(String name) {
        this(name, 200);
    }

    protected Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public int getCountHands() {
        return hands.size();
    }

    public boolean isActivePlayer() {
        return isActive;
    }

    private void deactivatePlayer() {
        if (balance <= 0) {
            isActive = false;
            balance = -1;
        }
    }

    public boolean isBusted(int index) {
        return getTotal(index) > Blackjack.WIN_TOTAL;
    }

    public void bust(int index) {
        printStatus(index, " busts.");
        deactivatePlayer();
    }

    public void win(int index) {
        Hand hand = hands.get(index);
        printStatus(index, " wins.");
        if (isBlackjack(index)) {
            balance += 3 * hand.getBet();
        } else {
            balance += 2 * hand.getBet();
        }
    }

    public void bankrupt() {
        System.out.println(name + " is bankrupt.");
        deactivatePlayer();
    }

    public void lose(int index) {
        printStatus(index, " loses.");
        deactivatePlayer();
    }

    public void draw(int index) {
        printStatus(index, " draw");
        balance += hands.get(index).getBet();
    }

    private void printStatus(int index, String status) {
        System.out.println(name + "'s hand" + index + status);
    }

    public boolean isBlackjack(int index) {
        return hands.get(index).size() == 2 && getTotal(index) == Blackjack.WIN_TOTAL;
    }

    public void addCard(int index, Card card) {
        hands.get(index).addCard(card);
    }

    public int getTotal(int index) {
        Hand hand = hands.get(index);
        if (hand.isLose()) {
            return 0;
        }
        return hand.getTotal();
    }

    public void clear() {
        for (Hand hand : hands) {
            hand.clear();
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(name + ": ");
        if (isActivePlayer()) {
            for (int i = 0; i < hands.size(); i++) {
                Hand hand = hands.get(i);
                string.append("hand" + i + "\t" + hand);
                if (hand.isLose()) {
                    string.append("loses");
                } else {
                    int total = hand.getTotal();
                    if (total != 0) {
                        string.append("(" + total + ")\t");
                    }
                }
            }
            string.append(balance + "$");
        } else {
            string.append("bankrupt");
        }
        return string.toString();
    }

    public abstract boolean isHitting(int index);

    public abstract void makeBet();
}
