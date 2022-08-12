package controls;

import resources.Algo;
import values.Card;

import java.util.List;
import java.util.Map;

public abstract class AbstractGameState {
    Algo algo;
    static boolean isATurn;
    boolean isSucceedLatestAttack;

    public AbstractGameState(Algo algo) {
        this.algo = algo;
        isATurn = algo.isATurn();
    }
    public abstract void setAttacker(int attacker);
    public abstract void setTarget(int target);
    public abstract void setGuess(int guess);
    public abstract void attack();


    public List<Card> getMyHands() {
        return algo.getHandsA();
    }

    public List<Card> getOpponentHands() {
        return algo.getHandsB();
    }

    public Card getTopCard() {
        if (isDeckLess()) return null;
        return algo.getDeck().get(0);
    }

    public void updateTurn() {
        algo.updateTurn();
        isATurn = algo.isATurn();
    }

    public boolean isATurn() {
        return isATurn;
    }

    public boolean isDeckLess() {
        return algo.getDeck().size() == 0;
    }

    public boolean isSucceedLatestAttack() {
        return isSucceedLatestAttack;
    }

    public int getDeckNumber() {
        return algo.getDeck().size();
    }

    public boolean isALose() {
        return algo.getLoseA();
    }

    public boolean isBLose() {
        return algo.getLoseB();
    }
}
