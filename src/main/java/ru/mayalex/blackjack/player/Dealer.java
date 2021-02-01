package ru.mayalex.blackjack.player;

import ru.mayalex.blackjack.deck.Hand;

public class Dealer extends Player {

    public Dealer() {
        super("Dealer");
        hands.add(new Hand(0));
    }

    @Override
    public boolean isHitting(int index) {
        return getTotal(index) <= 16;
    }

    @Override
    public void makeBet() {

    }

    public int getTotal() {
        return getTotal(0);
    }

    public boolean isBusted() {
        return isBusted(0);
    }

    public void flipFirstCard() {
        Hand hand = hands.get(0);
        if (hand.isEmpty()) {
            System.out.println("No card to flip!");
        } else {
            hand.getCard(0).flip();
        }
    }
}
