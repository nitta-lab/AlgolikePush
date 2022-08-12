package resources;

import java.util.AbstractMap;
import java.util.Map;

public class ResultBySelectingA {
    private HandsB handsB;
    private HandsA handsA;

    private AttackerA attackerA;
    private TargetA targetA;
    private GuessA guessA;

    private Map.Entry<Boolean, Map.Entry<Integer, Integer>> value;

    public ResultBySelectingA(HandsB handsB, HandsA handsA, AttackerA attackerA, TargetA targetA, GuessA guessA) {
        this.handsB = handsB;
        this.handsA = handsA;
        this.attackerA = attackerA;
        this.targetA = targetA;
        this.guessA = guessA;
    }

    public void selectAndAttackA() {
        this.value = new AbstractMap.SimpleEntry<>((handsB.getValue().get(targetA.getValue()).getNumber() == guessA.getValue()), new AbstractMap.SimpleEntry<>(targetA.getValue(), attackerA.getValue()));
        this.handsA.updateResultBySelectingA(value);
        this.handsB.updateResultBySelectingA(value);
    }

    public Map.Entry<Boolean, Map.Entry<Integer, Integer>> getValue() {
        return value;
    }
}