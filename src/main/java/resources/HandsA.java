package resources;

import values.Card;

import java.util.*;

public class HandsA {
    private Map.Entry<Boolean, Map.Entry<Integer, Integer>> resultBySelectingA;
    private Map.Entry<Boolean, Map.Entry<Integer, Integer>> resultBySelectingB;
    private Map.Entry<Boolean, Integer> resultByDrawingB;
    private Map.Entry<Boolean, Integer> resultByDrawingA;
    private List<Card> value = new ArrayList<Card>();

    public List<Card> getValue() {
        return value;
    }

    public void updateResultBySelectingA(Map.Entry<Boolean, Map.Entry<Integer, Integer>> resultBySelectingA) {
        this.resultBySelectingA = resultBySelectingA;
        List<Card> temp_if3;
        if (resultBySelectingA.getKey()) {
            temp_if3 = this.value;
        } else {
            this.value.set(resultBySelectingA.getValue().getValue(), new Card(this.value.get(resultBySelectingA.getValue().getValue()).getNumber(), true));
            temp_if3 = this.value;
        }
        value = temp_if3;
    }

    public void updateResultBySelectingB(Map.Entry<Boolean, Map.Entry<Integer, Integer>> resultBySelectingB) {
        this.resultBySelectingB = resultBySelectingB;
        List<Card> temp_if4;
        if (resultBySelectingB.getKey()) {
            this.value.set(resultBySelectingB.getValue().getKey(), new Card(this.value.get(resultBySelectingB.getValue().getKey()).getNumber(), true));
            temp_if4 = this.value;
        } else {
            temp_if4 = this.value;
        }
        value = temp_if4;
    }

    public void updateResultByDrawingB(Map.Entry<Boolean, Integer> resultByDrawingB, List<Card> deck) {
        this.resultByDrawingB = resultByDrawingB;
        List<Card> temp_if6;
        if (resultByDrawingB.getKey()) {
            this.value.set(resultByDrawingB.getValue(), new Card(this.value.get(resultByDrawingB.getValue()).getNumber(), true));
            temp_if6 = this.value;
        } else {
            temp_if6 = this.value;
        }
        value = temp_if6;
    }

    public void updateResultByDrawingA(Map.Entry<Boolean, Integer> resultByDrawingA, List<Card> deck) {
        this.resultByDrawingA = resultByDrawingA;
        List<Card> temp_if7;
        if (resultByDrawingA.getKey()) {
            this.value.add(0, new Card(deck.get(0).getNumber(), false));
            this.value.sort(Comparator.comparing(Card::getNumber));
            temp_if7 = this.value;
        } else {
            this.value.add(0, new Card(deck.get(0).getNumber(), true));
            this.value.sort(Comparator.comparing(Card::getNumber));
            temp_if7 = this.value;
        }
        value = temp_if7;
    }
}