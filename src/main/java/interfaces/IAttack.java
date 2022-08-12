package interfaces;

import java.util.List;
import java.util.Map;

public interface IAttack {
    /**
     *
     */
    void attack(int guess, int attacker, int target);

    List<Map.Entry<Integer, Boolean>> getMyHands();

    List<Map.Entry<Integer, Boolean>> getOpponentHands();

    Map.Entry<Integer, Boolean> getTopCard();

    boolean isDeckLess();

    int getDeckNumber();

    boolean isSucceedLatestAttack();

    boolean updateTurn();

    boolean isATurn();

    boolean isALose();

    boolean isBLose();
}
