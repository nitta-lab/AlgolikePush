package resources;

import values.Card;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static views.Constants.DECK_COUNT;
import static views.Constants.HAND_COUNT;

public class Algo {
    private LoseB loseB;
    private HandsB handsB;
    private LoseA loseA;
    private HandsA handsA;
    private Deck deck;
    private ResultByDrawingA resultByDrawingA;
    private ResultByDrawingB resultByDrawingB;
    private ResultBySelectingA resultBySelectingA;
    private ResultBySelectingB resultBySelectingB;

    private AttackerA attackerA;
    private AttackerB attackerB;
    private TargetA targetA;
    private TargetB targetB;
    private GuessA guessA;
    private GuessB guessB;


    int turnCount = 0;
    boolean isATurn;

    public Algo() {
        ///モデル生成分
        loseB = new LoseB();
        handsB = new HandsB(loseB);
        loseA = new LoseA();
        handsA = new HandsA(loseA);
        deck = new Deck();
        attackerA = new AttackerA();
        attackerB = new AttackerB();
        targetA = new TargetA();
        targetB = new TargetB();
        guessA = new GuessA();
        guessB = new GuessB();

        resultByDrawingA = new ResultByDrawingA(handsB, deck, handsA, targetA, guessA);
        resultByDrawingB = new ResultByDrawingB(deck, handsB, handsA, targetB, guessB);
        resultBySelectingA = new ResultBySelectingA(handsB, handsA, attackerA, targetA, guessA);
        resultBySelectingB = new ResultBySelectingB(handsA, handsB, attackerB, targetB, guessB);
        //追加分
        isATurn = true;
        deck.init(DECK_COUNT);

    }

    public void setAttackerA(int a) {
        this.attackerA.setAttackerA(a);
    }

    public void setAttackerB(int b) {
        this.attackerB.setAttackerB(b);
    }

    public void setTargetA(int a) {
        this.targetA.setTargetA(a);
    }

    public void setTargetB(int b) {
        this.targetB.setTargetB(b);
    }

    public void setGuessA(int a) {
        this.guessA.setGuessA(a);
    }

    public void setGuessB(int b) {
        this.guessB.setGuessB(b);
    }

    public void drawAndAttackA() {
        this.resultByDrawingA.drawAndAttackA();
    }

    public void selectAndAttackA() {
        this.resultBySelectingA.selectAndAttackA();
    }

    public void drawAndAttackB() {
        this.resultByDrawingB.drawAndAttackB();
    }

    public void selectAndAttackB() {
        this.resultBySelectingB.selectAndAttackB();
    }

    public Map.Entry<Boolean, Integer> getResultByDrawingA() {
        return resultByDrawingA.getValue();
    }

    public Map.Entry<Boolean, Integer> getResultByDrawingB() {
        return resultByDrawingB.getValue();
    }

    public Map.Entry<Boolean, Map.Entry<Integer, Integer>> getResultBySelectingA() {
        return resultBySelectingA.getValue();
    }

    public Map.Entry<Boolean, Map.Entry<Integer, Integer>> getResultBySelectingB() {
        return resultBySelectingB.getValue();
    }

    public List<Card> getDeck() {
        return deck.getValue();
    }

    public boolean getLoseA() {
        return loseA.getValue();
    }

    public boolean getLoseB() {
        return loseB.getValue();
    }

    public List<Card> getHandsA() {
        return handsA.getValue();
    }

    public List<Card> getHandsB() {
        return handsB.getValue();
    }



    //追加分
    /**
     * 各プレイヤーに手札を配るメソッド
     */
    public void distributeHands(){
        for (int i = 0; i < HAND_COUNT; i++) {
            handsA.getValue().add(deck.head());
            handsB.getValue().add(deck.head());
        }
        getHandsA().sort(Comparator.comparing(Card::getNumber));
        getHandsB().sort(Comparator.comparing(Card::getNumber));
    }
    public void setDeck(int... param){
        deck.setValue(param);
    }
    public boolean isATurn() {
        return isATurn;
    }

    public void updateTurn() {
        isATurn = !isATurn;
    }
}