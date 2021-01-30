package ru.mayalex.blackjack.player;

public class Dealer extends AbstractPlayer {

    public Dealer() {
        super("Dealer");
    }

    @Override
    public boolean isHitting() {
        return getTotal() <= 16;
    }

    public void flipFirstCard() {
        if (cards.isEmpty()) {
            System.out.println("No card to flip!");
        } else {
            cards.get(0).flip();
        }
    }
}
