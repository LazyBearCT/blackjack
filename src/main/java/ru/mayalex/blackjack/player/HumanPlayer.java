package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.Blackjack;
import ru.mayalex.blackjack.util.InputChecker;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public boolean isHitting() {
        if (getTotal() == Blackjack.WIN_TOTAL) {
            return false;
        }
        return InputChecker.askPlayer(name + ", do want a hit? (y/n): ");
    }

    @Override
    public void makeBet() {
        bet = InputChecker.getCount(Math.min(MAX_BET, balance), "Your bet is (1 - " + Math.min(MAX_BET, balance) + "): ");
        balance -= bet;
    }
}
