package controls;

import resources.Algo;

public class TurnPlayer extends AbstractGameState {
    public TurnPlayer(Algo algo) {
        super(algo);
    }

    @Override
    public void setAttacker(int attacker) {
        algo.setAttackerB(attacker);
    }

    @Override
    public void setTarget(int target) {
        algo.setTargetA(target);
    }

    @Override
    public void setGuess(int guess) {
        algo.setGuessA(guess);
    }

    @Override
    public void attack() {
        if (isDeckLess()) {
            algo.selectAndAttackA();
            isSucceedLatestAttack = algo.getResultBySelectingA().getKey();
        } else {
            algo.drawAndAttackA();
            isSucceedLatestAttack = algo.getResultByDrawingA().getKey();
        }
    }
}
