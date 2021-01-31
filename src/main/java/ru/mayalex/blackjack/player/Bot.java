package ru.mayalex.blackjack.player;

import java.util.Random;

public class Bot extends Player {

    private static final Random random = new Random();

    public Bot(int number) {
        super("Bot" + number);
    }

    @Override
    public boolean isHitting(int index) {
        return getTotal(index) <= 16;
    }

    @Override
    public void makeBet(int index) {
        bets.add(index, 1 + random.nextInt(Math.min(MAX_BET, balance)));
        balance -= bets.get(index);
    }
}
