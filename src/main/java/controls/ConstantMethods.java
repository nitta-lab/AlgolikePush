package controls;

import resources.Algo;
import values.Card;

import java.util.Map;

public class ConstantMethods {
    public static void printAlgoState(Algo algo) {
        String s = "";
        s += "deck:\n";
        for (Card i : algo.getDeck()) {
            s += "\t" + i.getNumber() + "\t" + (i.isFace() ? "open" : "closed") + "\n";
        }
        s += "handsA:\n";
        for (Card i : algo.getHandsA()) {
            s += "\t" + i.getNumber() + "\t" + (i.isFace() ? "open" : "closed") + "\n";
        }
        s += "handsB:\n";
        for (Card i : algo.getHandsB()) {
            s += "\t" + i.getNumber() + "\t" + (i.isFace() ? "open" : "closed") + "\n";
        }
        s += "\n";
        if (algo.getLoseA()) s += "B win.\n";
        else if (algo.getLoseB()) s += "A win.\n";
        System.out.println(s);
    }
}
