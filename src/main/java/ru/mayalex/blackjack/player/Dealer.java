package ru.mayalex.blackjack.player;

public class Dealer extends Player {

    public Dealer() {
        super("Dealer");
    }

    @Override
    public boolean isHitting(int index) {
        return getTotal(index) <= 16;
    }

    @Override
    public void makeBet(int index) {
        bets.add(index, 0);
    }

    public int getTotal() {
        return getTotal(0);
    }

    public boolean isBusted() {
        return isBusted(0);
    }

    public void flipFirstCard() {
        if (hands.get(0).isEmpty()) {
            System.out.println("No card to flip!");
        } else {
            hands.get(0).getCard(0).flip();
        }
    }
}
