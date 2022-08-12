package resources;

import values.Card;

import java.util.*;

public class HandsB {
    private Map.Entry<Boolean, Integer> succeedDrawB;
    private Map.Entry<Boolean, Map.Entry<Integer, Integer>> succeedSelectA;
    private Map.Entry<Boolean, Integer> succeedDrawA;
    private Map.Entry<Boolean, Map.Entry<Integer, Integer>> succeedSelectB;
    private LoseB loseB;
    private List<Card> value = new ArrayList<Card>();

    public HandsB(LoseB loseB) {
        this.loseB = loseB;
    }

    public List<Card> getValue() {
        return value;
    }

    public void updateResultByDrawingB(Map.Entry<Boolean, Integer> succeedDrawB, List<Card> deck) {
        this.succeedDrawB = succeedDrawB;
        List<Card> temp_if0;
        if (succeedDrawB.getKey()) {
            this.value.add(0, new Card(deck.get(0).getNumber(), false));
            this.value.sort(Comparator.comparing(Card::getNumber));
            temp_if0 = this.value;
        } else {
            this.value.add(0, new Card(deck.get(0).getNumber(), true));
            this.value.sort(Comparator.comparing(Card::getNumber));
            temp_if0 = this.value;
        }
        value = temp_if0;
        this.loseB.updateHandsB(value);
    }

    public void updateResultBySelectingA(Map.Entry<Boolean, Map.Entry<Integer, Integer>> succeedSelectA) {
        this.succeedSelectA = succeedSelectA;
        List<Card> temp_if1;
        if (succeedSelectA.getKey()) {
            this.value.set(succeedSelectA.getValue().getKey(), new Card(this.value.get(succeedSelectA.getValue().getKey()).getNumber(), true));
            temp_if1 = this.value;
        } else {
            temp_if1 = this.value;
        }
        value = temp_if1;
        this.loseB.updateHandsB(value);
    }

    public void updateResultByDrawingA(Map.Entry<Boolean, Integer> succeedDrawA, List deck) {
        this.succeedDrawA = succeedDrawA;
        List<Card> temp_if2;
        if (succeedDrawA.getKey()) {
            this.value.set(succeedDrawA.getValue(), new Card(this.value.get(succeedDrawA.getValue()).getNumber(), true));
            temp_if2 = this.value;
        } else {
            temp_if2 = this.value;
        }
        value = temp_if2;
        this.loseB.updateHandsB(value);
    }

    public void updateResultBySelectingB(Map.Entry<Boolean, Map.Entry<Integer, Integer>> succeedSelectB) {
        this.succeedSelectB = succeedSelectB;
        List<Card> temp_if5;
        if (succeedSelectB.getKey()) {
            temp_if5 = this.value;
        } else {
            this.value.set(succeedSelectB.getValue().getValue(), new Card(this.value.get(succeedSelectB.getValue().getValue()).getNumber(), true));
            temp_if5 = this.value;
        }
        value = temp_if5;
        this.loseB.updateHandsB(value);
    }
}