package controls;

import resources.Algo;

public class TurnBot extends AbstractGameState {
    public TurnBot(Algo algo) {
        super(algo);
    }

    @Override
    public void setAttacker(int attacker) {
        algo.setAttackerB(attacker);
    }

    @Override
    public void setTarget(int target) {
        algo.setTargetB(target);
    }

    @Override
    public void setGuess(int guess) {
        algo.setGuessB(guess);
    }

    @Override
    public void attack() {
        if (isDeckLess()) {
            algo.selectAndAttackB();
            isSucceedLatestAttack = algo.getResultBySelectingB().getKey();
        } else {
            algo.drawAndAttackB();
            isSucceedLatestAttack = algo.getResultByDrawingB().getKey();
        }
    }
}
