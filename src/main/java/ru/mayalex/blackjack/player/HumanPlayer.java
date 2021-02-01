package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.Blackjack;
import ru.mayalex.blackjack.deck.Hand;
import ru.mayalex.blackjack.util.InputChecker;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public boolean isHitting(int index) {
        Hand hand = hands.get(index);
        if (hand.isLimit()) {
            return false;
        }
        if (getTotal(index) == Blackjack.WIN_TOTAL) {
            return false;
        }
        String message = "";
        List<Integer> values = new ArrayList<>();
        if (hand.size() == 2) {
            message += "Enter 1, if you want lose.\nEnter 2, if you want double.\n";
            values.add(1);
            values.add(2);
            if (hand.getCard(0).getValue() == hand.getCard(1).getValue()) {
                message += "Enter 3, if you want split.\n";
                values.add(3);
            }
        }
        message += "Enter 4, if you want hit.\nEnter 5 or not: ";
        values.add(4);
        values.add(5);
        int choice = InputChecker.getCount(count -> InputChecker.checkCount(count, values), message);
        int bet = hand.getBet();
        switch (choice) {
            case 1: //lose
                balance += hand.lose();
                return false;
            case 2: //double
                balance -= bet;
                hands.set(index, hand.doubleBet());
                return !isBankrupt();
            case 3: //split
                hands.add(index + 1, hand.split());
                return !isBankrupt();
            case 4: //hit
                return true;
            case 5:
            default:
                return false;
        }
    }

    private boolean isBankrupt() {
        if (balance < 0) {
            isActive = false;
            System.out.println(name + "is bankrupt");
            return true;
        }
        return false;
    }

    @Override
    public void makeBet() {
        int min = Math.min(MAX_BET, balance);
        Hand hand = new Hand(InputChecker.getCount(min, "Your bet is (1 - " + min + "): "));
        hands.add(hand);
        balance -= hand.getBet();
    }
}
