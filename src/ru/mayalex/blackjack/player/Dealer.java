package ru.mayalex.blackjack.player;

public class Dealer extends AbstractPlayer {

    public Dealer(String name) {
        super(name);
    }

    @Override
    public boolean isHitting() {
        return hand.getTotal() <= 16;
    }

    public void flipFirstCard() {
        if (hand.isEmpty()) {
            System.out.println("No card to flip!");
        } else {
            hand.getCard(1).flip();
        }
    }
}
