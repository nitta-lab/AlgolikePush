package resources;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ResultByDrawingB {
    private Deck deck;
    private HandsB handsB;
    private HandsA handsA;
    private TargetB targetB;
    private GuessB guessB;
    private Map.Entry<Boolean, Integer> value;

    public ResultByDrawingB(Deck deck, HandsB handsB, HandsA handsA, TargetB targetB, GuessB guessB) {
        this.deck = deck;
        this.handsB = handsB;
        this.handsA = handsA;
        this.targetB = targetB;
        this.guessB = guessB;
    }

    public void drawAndAttackB() {
        this.value = new AbstractMap.SimpleEntry<>((handsA.getValue().get(targetB.getValue()).getNumber() == guessB.getValue()), targetB.getValue());

        List deck = this.deck.getValue();
        this.handsB.updateResultByDrawingB(value, deck);
        this.handsA.updateResultByDrawingB(value, deck);
        this.deck.updateResultByDrawingB(value);
    }

    public Map.Entry<Boolean, Integer> getValue() {
        return value;
    }
}