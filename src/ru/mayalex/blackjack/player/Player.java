package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.util.InputChecker;

public class Player extends AbstractPlayer {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isHitting() {
        return InputChecker.askPlayer(name + ", do want a hit? (y/n): ");
    }
}
