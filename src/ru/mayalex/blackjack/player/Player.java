package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.Blackjack;
import ru.mayalex.blackjack.util.InputChecker;

public class Player extends AbstractPlayer {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isHitting() {
        if (hand.getTotal() == Blackjack.WIN_TOTAL) {
            return false;
        }
        return InputChecker.askPlayer(name + ", do want a hit? (y/n): ");
    }
}
