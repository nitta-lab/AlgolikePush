package resources;

import values.Card;

import java.util.*;

public class Deck {
    private Map.Entry<Boolean, Integer> succeedDrawB;
    private Map.Entry<Boolean, Integer> succeedDrawA;
    private List<Card> value = new ArrayList<>();

    //added
    public void init(int num) {
        for (int i = 0; i < num; i++) {
            this.value.add(new Card(i, false));
        }
        Collections.shuffle(this.value);
    }

    public void setValue(int... params){
        this.value.clear();
        for (int i = 0; i < params.length; i++){
            this.value.add(new Card(i, false));
        }
    }
    //added
    public Card head() {
        Card card = getValue().get(0);
        getValue().remove(getValue().get(0));
        return card;
    }

    public List<Card> getValue() {
        return value;
    }

    public void updateResultByDrawingB(Map.Entry<Boolean, Integer> succeedDrawB) {
        this.succeedDrawB = succeedDrawB;
        value = this.value.subList(1, this.value.size());
    }

    public void updateResultByDrawingA(Map.Entry<Boolean, Integer> succeedDrawA) {
        this.succeedDrawA = succeedDrawA;
        value = this.value.subList(1, this.value.size());
    }
}