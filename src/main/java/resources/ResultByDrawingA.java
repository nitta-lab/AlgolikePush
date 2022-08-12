package resources;


import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ResultByDrawingA {
    private HandsB handsB;
    private Deck deck;
    private HandsA handsA;
    private TargetA targetA;
    private GuessA guessA;
    private Map.Entry<Boolean, Integer> value;

    public ResultByDrawingA(HandsB handsB, Deck deck, HandsA handsA, TargetA targetA, GuessA guessA) {
        this.handsB = handsB;
        this.deck = deck;
        this.handsA = handsA;
        this.targetA = targetA;
        this.guessA = guessA;
    }

    public void drawAndAttackA() {
        this.value = new AbstractMap.SimpleEntry<>((handsB.getValue().get(targetA.getValue()).getNumber() == guessA.getValue()), targetA.getValue());
        List deck = this.deck.getValue();
        this.handsA.updateResultByDrawingA(value, deck);
        this.handsB.updateResultByDrawingA(value, deck);
        this.deck.updateResultByDrawingA(value);
    }

    public Map.Entry<Boolean, Integer> getValue() {
        return value;
    }
}