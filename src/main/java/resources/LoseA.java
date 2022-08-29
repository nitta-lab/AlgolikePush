package resources;

import values.Card;

public class LoseA {
    HandsA handsA;
    
    public LoseA(HandsA handsA) {
    	this.handsA = handsA;
    }

    public boolean getValue() {
        boolean result = true;
        for (Card card : handsA.getValue()) {
            if (!card.isFace()) {
                result = false;
                break;
            }
        }
        return result;
    }
}