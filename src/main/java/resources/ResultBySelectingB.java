package resources;

import java.util.AbstractMap;
import java.util.Map;

public class ResultBySelectingB {
    private HandsA handsA;
    private HandsB handsB;
    private AttackerB attackerB;
    private TargetB targetB;
    private GuessB guessB;
    private Map.Entry<Boolean, Map.Entry<Integer, Integer>> value;

    public ResultBySelectingB(HandsA handsA, HandsB handsB, AttackerB attackerB, TargetB targetB, GuessB guessB) {
        this.handsA = handsA;
        this.handsB = handsB;
        this.attackerB = attackerB;
        this.targetB = targetB;
        this.guessB = guessB;
    }

    public void selectAndAttackB() {
        this.value = new AbstractMap.SimpleEntry<>((handsA.getValue().get(targetB.getValue()).getNumber() == guessB.getValue()), new AbstractMap.SimpleEntry<>(targetB.getValue(), attackerB.getValue()));
        this.handsB.updateResultBySelectingB(value);
        this.handsA.updateResultBySelectingB(value);
    }

    public Map.Entry<Boolean, Map.Entry<Integer, Integer>> getValue() {
        return value;
    }
}