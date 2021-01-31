package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.Blackjack;
import ru.mayalex.blackjack.deck.Hand;
import ru.mayalex.blackjack.util.InputChecker;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {

    private List<Boolean> isLimit = initIsLimit();

    private List<Boolean> initIsLimit() {
        List<Boolean> isLimit = new ArrayList<>();
        isLimit.add(false);
        return isLimit;
    }

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public boolean isHitting(int index) {
        if (hands.get(index).size() <= 2) {
            isLimit.set(index, false);
        }
        if (isLimit.get(index)) {
            return false;
        }
        if (getTotal(index) == Blackjack.WIN_TOTAL) {
            return false;
        }
        String message = "";
        Hand hand = hands.get(index);
        if (hand.size() == 2) {
            message += "Enter 1, if you want lose.\nEnter 2, if you want double.\n";
            if (hand.getCard(0).getValue() == hand.getCard(1).getValue()) {
                message += "Enter 3, if you want split.\n";
            }
        }
        message += "Enter 4, if you want hit.\nEnter 5 or not: ";
        int choice = InputChecker.getCount(5, message);
        switch (choice) {
            case 1: //lose
                isLose.set(index, true);
                for (int i = 2; i <= bets.get(index); i++) {
                    if (bets.get(index) % i == 0) {
                        balance += bets.get(index) / i;
                        break;
                    }
                }
                return false;
            case 2: //double
                balance -= bets.get(index);
                bets.set(index, bets.get(index) * 2);
                isLimit.set(index, true);
                return !isBankrupt();
            case 3: //split
                hands.add(index + 1, hands.get(index).split());
                bets.add(index + 1, bets.get(index));
                isLose.add(index + 1, false);
                isLimit.add(index + 1, false);
                return !isBankrupt();
            case 4:
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
    public void makeBet(int index) {
        bets.add(index, InputChecker.getCount(Math.min(MAX_BET, balance), "Your bet is (1 - " + Math.min(MAX_BET, balance) + "): "));
        balance -= bets.get(index);
    }
}
