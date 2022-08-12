package resources;

import values.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoseB {
    private boolean value;

    public void updateHandsB(List<Card> handsB) {
        boolean result = true;
        for (Card card : handsB) {
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