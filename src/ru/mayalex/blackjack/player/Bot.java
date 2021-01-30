package ru.mayalex.blackjack.player;

public class Bot extends AbstractPlayer {

    public Bot(int number) {
        super("Bot" + number);
    }

    @Override
    public boolean isHitting() {
        return getTotal() <= 16;
    }
}
