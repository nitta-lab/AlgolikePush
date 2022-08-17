package resources;

import values.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoseA {
    HandsA handsA;
    
    public LoseA(HandsA handsA) {
    	this.handsA = handsA;
    }

    public boolean getValue() {
        boolean result = true;
        for (Card card : this.handsA.getValue()) {
            if (!card.isFace()) {
                result = false;
                break;
            }
        }
        return result;
    }
}