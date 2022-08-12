package controls;

import interfaces.IGameView;
import resources.Algo;

public class PhaseController {
    IGameView iGameView;
    Phase currentPhase;
    /// guess;
    boolean isDecidedAttacker;
    //int attacker;
   /// int target;
    Algo algo;
    public AbstractGameState abstractGameState;
    TurnPlayer turnPlayer;
    TurnBot turnBot;

    public PhaseController(Algo algo, IGameView iGameView) {
        this.algo = algo;
        isDecidedAttacker = false;
        turnPlayer = new TurnPlayer(algo);
        turnBot = new TurnBot(algo);
        abstractGameState = turnPlayer;
        currentPhase = Phase.Selection;
        this.iGameView = iGameView;
    }

    public void startGame() {
        changePhase(Phase.StartPlayerTurn);
    }

    public void setSelection(int attacker) {
        changePhase(Phase.Selection, attacker);
    }

    public void setTarget(int target) {
        changePhase(Phase.Target, target);

    }

    public boolean isDecidedAttacker() {
        return isDecidedAttacker;
    }

    /**
     * @param phase  遷移先の状態
     * @param params 遷移先に遷移するために必要な引数
     */
    private void changePhase(Phase phase, int... params) {

        switch (phase) {
            case StartPlayerTurn:
                abstractGameState = turnPlayer;
                isDecidedAttacker = !abstractGameState.isDeckLess();
                iGameView.repaintBoard(abstractGameState);
                iGameView.onStartPlayerTurn(abstractGameState);
                break;
            case Selection:

                //this.attacker = params[0];
                abstractGameState.setAttacker(params[0]);
                isDecidedAttacker = true;
                currentPhase = Phase.Target;
                iGameView.onDecidedSelection(params[0]);

                break;
            case Target:
                abstractGameState.setTarget(params[0]);
                //this.target = params[0];
                currentPhase = phase;
                iGameView.onDecidedTarget(params[0]);
                currentPhase = Phase.Declaration;

                break;
            case Declaration:
                //this.guess = params[0];
                abstractGameState.setGuess(params[0]);
                abstractGameState.attack();
                currentPhase = Phase.StartBotTurn;
                boolean isSucceed = abstractGameState.isSucceedLatestAttack();
                iGameView.repaintBoard(abstractGameState);
                iGameView.onFinishedPlayerAttack(params[0], isSucceed);
                if (judgeGameOver()) return;
                changePhase(Phase.StartBotTurn, 0);
                break;
            case StartBotTurn:

                currentPhase = Phase.StartBotTurn;
                abstractGameState = turnBot;
                abstractGameState.updateTurn();
                iGameView.onStartBotAttack(turnBot);

                break;
            case BotAttack:
                abstractGameState.updateTurn();
                abstractGameState.setAttacker(params[0]);
                abstractGameState.setTarget(params[1]);
                abstractGameState.setGuess(params[2]);
                abstractGameState.attack();
                iGameView.onFinishedBotAttack(params[2], abstractGameState.isSucceedLatestAttack());
                if (judgeGameOver()) return;
                changePhase(Phase.StartPlayerTurn);
                break;

        }
    }

    public void setDeclaration(int guess) {
        changePhase(Phase.Declaration, guess);
    }

    public void botAttack(int guess, int attacker, int target) {
        changePhase(Phase.BotAttack, attacker, target, guess);


    }

    boolean judgeGameOver() {

        if (algo.getLoseA()) {
            iGameView.onFinishedGame(abstractGameState, true);
            return true;
        } else if (algo.getLoseB()) {
            iGameView.onFinishedGame(abstractGameState, false);
            return true;
        }
        return false;
    }

    enum Phase {
        StartPlayerTurn,
        Selection,
        Target,
        Declaration,
        StartBotTurn,
        BotAttack
    }
}
