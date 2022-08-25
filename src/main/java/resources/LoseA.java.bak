package resources;

import values.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoseA {
    boolean value;

    public void updateHandsA(List<Card> handsA) {
        boolean result = true;
        for (Card card : handsA) {
            if (!card.isFace()) {
                result = false;
                break;
            }
        }
        value = result;
    }

    public boolean getValue() {
        return value;
    }
}