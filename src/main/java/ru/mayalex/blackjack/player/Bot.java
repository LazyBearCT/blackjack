package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.deck.Hand;

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
    public void makeBet() {
        Hand hand = new Hand(1 + random.nextInt(Math.min(MAX_BET, balance)));
        hands.add(hand);
        balance -= hand.getBet();
    }
}
