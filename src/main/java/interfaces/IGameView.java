package interfaces;

import controls.AbstractGameState;
import controls.TurnBot;

public interface IGameView {
    void onStartPlayerTurn(AbstractGameState abstractGameState);

    /**
     * アタックに使用するカードが決定した際に実行
     *
     * @param selection
     */
    void onDecidedSelection(int selection);

    /**
     * アタックの対象が決定した際に実行
     *
     * @param target
     */
    void onDecidedTarget(int target);

    /**
     * プレイヤーのアタックが終了した際に実行
     *
     * @param guess     宣言した数
     * @param isSucceed アタックの成否の結果
     */
    void onFinishedPlayerAttack(int guess, boolean isSucceed);

    /**
     * ボットのアタックが開始した際に実行
     *
     * @param turnBot ボットの思考結果を出力するためのAI
     */
    void onStartBotAttack(TurnBot turnBot);

    /**
     * ボットのアタックが終了した際に実行
     *
     * @param guess
     * @param isSucceed アタックの成否の結果
     */
    void onFinishedBotAttack(int guess, boolean isSucceed);

    /**
     * ゲームが終了した際に実行
     */
    void onFinishedGame(AbstractGameState abstractGameState, boolean isLoseA);

    /**
     * 盤面を再描画する
     *
     * @param abstractGameState
     */
    void repaintBoard(AbstractGameState abstractGameState);
}
