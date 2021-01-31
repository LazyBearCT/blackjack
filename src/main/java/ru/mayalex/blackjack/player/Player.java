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
    protected List<Hand> hands = initHands();

    private static List<Hand> initHands() {
        List<Hand> hands = new ArrayList<>();
        hands.add(new Hand());
        return hands;
    }

    protected int balance;
    protected List<Integer> bets = new ArrayList<>();
    protected boolean isActive = true;
    protected List<Boolean> isLose = initIsLose();

    private static List<Boolean> initIsLose() {
        List<Boolean> isLose = new ArrayList<>();
        isLose.add(false);
        return isLose;
    }

    protected Player(String name) {
        this(name, 200);
    }

    protected Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int getCountHands() {
        return hands.size();
    }

    public boolean isActivePlayer() {
        return isActive;
    }

    public void deactivatePlayer() {
        isActive = false;
    }

    public boolean isBusted(int index) {
        return getTotal(index) > Blackjack.WIN_TOTAL;
    }

    public void bust(int index) {
        System.out.println(name + "'s hand" + index + " busts.");
    }

    public void win(int index) {
        System.out.println(name + "'s hand" + index + " wins.");
        if (isBlackjack(index)) {
            balance += 3 * bets.get(index);
        } else {
            balance += 2 * bets.get(index);
        }
    }

    public void bankrupt() {
        System.out.println(name + " is bankrupt.");
        balance = 0;
    }

    public void lose(int index) {
        isLose.set(index, false);
        System.out.println(name + "'s hand" + index + " loses.");
    }

    public void draw(int index) {
        System.out.println(name + "'s hand" + index + " draw");
        balance += bets.get(index);
    }

    public boolean isBlackjack(int index) {
        return hands.get(index).size() == 2 && getTotal(index) == Blackjack.WIN_TOTAL;
    }

    public void addCard(int index, Card card) {
        hands.get(index).addCard(card);
    }

    public int getTotal(int index) {
        if (isLose.get(index)) {
            return 0;
        }
        return hands.get(index).getTotal();
    }

    public void clear() {
        for (Hand hand : hands) {
            hand.clear();
        }
        bets.clear();
        Collections.fill(isLose, false);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(name + ": ");
        if (isActivePlayer()) {
            for (int i = 0; i < hands.size(); i++) {
                string.append("hand" + i + "\t" + hands.get(i));
                if (isLose.get(i)) {
                    string.append("loses");
                } else {
                    int total = getTotal(i);
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

    public abstract void makeBet(int index);
}
