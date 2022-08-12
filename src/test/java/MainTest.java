import org.junit.jupiter.api.Test;
import resources.Algo;

import static controls.ConstantMethods.printAlgoState;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    void test(){
        Algo algo = new Algo();
        //デッキに0~7のカードを準備する
        algo.setDeck(0, 1, 2, 3, 4, 5, 6, 7);
        algo.distributeHands();
        printAlgoState(algo);

        //Aはカードを1枚引き、Bの0番目(リストのインデックス)のカードに対して「3」を宣言する。
        algo.setTargetA(0);
        algo.setGuessA(3);
        algo.drawAndAttackA();

        //アタックは失敗する。
        assertEquals(false, algo.getResultByDrawingA().getKey());

        assertEquals(false, algo.getLoseA());
        assertEquals(false, algo.getLoseB());

        //Bはカードを1枚引き、Aの2番目のカードに対して「2」を宣言する。
        algo.setTargetB(2);
        algo.setGuessB(2);
        algo.drawAndAttackB();

        //アタックは失敗する。
        assertEquals(false, algo.getResultByDrawingB().getKey());

        assertEquals(false, algo.getLoseA());
        assertEquals(false, algo.getLoseB());

        //Aはカードを1枚引き、Bの2番目のカードに対して「3」を宣言する。
        algo.setTargetA(2);
        algo.setGuessA(3);
        algo.drawAndAttackA();

        //アタックは失敗する。
        assertEquals(false, algo.getResultByDrawingA().getKey());

        assertEquals(false, algo.getLoseA());
        assertEquals(false, algo.getLoseB());

        //Bはカードを1枚引き、Aの0番目のカードに対して「0」を宣言する。
        algo.setTargetB(0);
        algo.setGuessB(0);
        algo.drawAndAttackB();

        //アタックは成功する。
        assertEquals(true, algo.getResultByDrawingB().getKey());

        assertEquals(false, algo.getLoseA());
        assertEquals(false, algo.getLoseB());

        //Aは手札の1番目のカードアタックに使用し、Bの0番目のカードに対して「3」を宣言する。
        algo.setAttackerA(1);
        algo.setTargetA(0);
        algo.setGuessA(3);
        algo.selectAndAttackA();

        //アタックは失敗する。
        assertEquals(false, algo.getResultBySelectingA().getKey());

        //Aの手札が全て表になったので、Aは敗北する。
        assertEquals(true, algo.getLoseA());
        assertEquals(false, algo.getLoseB());

        printAlgoState(algo);
    }
}
