package controls;

import interfaces.IBotBehavior;
import values.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static views.Constants.DECK_COUNT;

public class BotIntelligence implements IBotBehavior {
    TurnBot turnBot;

    public BotIntelligence(TurnBot turnBot) {
        this.turnBot = turnBot;
    }

    /**
     * 未確定の数字を列挙する
     */
    List<Integer> calculateCandidate() {
        //候補の初期化
        List<Integer> candidates = new ArrayList<Integer>();
        for (int i = 0; i < DECK_COUNT; i++) candidates.add(i);

        candidates.removeAll(turnBot.getOpponentHands().stream().map(x -> x.isFace()).collect(Collectors.toList()));
        candidates.removeAll(turnBot.getMyHands().stream().filter(x -> x.isFace()).map(x -> x.getNumber()).collect(Collectors.toList()));

        return candidates;
    }

    /**
     * 相手の手札に対して、候補となる数字を割り当てる
     *
     * @return 候補のリスト
     */
    List<int[]> assignCandidateNumberEachHand() {
        List<Card> myHands = turnBot.getMyHands();
        List<int[]> result = new ArrayList<int[]>();

        for (Card card : myHands) {//ユーザーの手札を左から見ていく
            int index = myHands.indexOf(card);//左からindex番目に
            List<Integer> candidateList = calculateCandidate();//確認するカード


            if (card.isFace()) {
                candidateList.clear();
            } else {                  //裏の場合

                //そのカードから右隣のカードを確認していく
                for (int i = 0; i < myHands.size() - index - 1; i++) {
                    //今確認していっているカード
                    Card currentCard = myHands.get(i + index + 1);
                    if (currentCard.isFace()) {//確認しているカードが表なら、(YES)
                        candidateList.removeIf(x -> x > currentCard.getNumber());//その確認したカードの数字より大きい数字を除外する。
                        for (int j = 1; j < i; j++) {//そして、その確認したカードよりjだけ小さいそのカードからj番目の数字を除外する。
                            candidateList.remove(candidateList.size() - 1);
                        }
                        break;
                    }
                }

                //そのカードから左隣のカードを確認していく
                for (int i = 1; i <= index; i++) {
                    //今確認していっているカード
                    Card currentCard = myHands.get(index - i);
                    if (currentCard.isFace()) {//表があれば、それより小さい数字を候補から削除する
                        candidateList.removeIf(x -> x < currentCard.getNumber());
                    }
                }


            }
            String s = (card.isFace() ? "+" : "-") + card.getNumber() + ":[";
            for (int i : candidateList) s += i + " ";
            s += "]\n";
            System.out.println(s);
            result.add(candidateList.stream().mapToInt(x -> x).toArray());
        }
        return result;
    }

    //公開されていないカードのインデックスを選ぶ(カードにかかれている番号ではない)
    @Override
    public int selectAttacker() {
        List<Card> botHands = turnBot.getOpponentHands();

        for (Card i : botHands) {//小さいものから選択
            if (!i.isFace()) return botHands.indexOf(i);
        }
        return 0;
    }

    @Override
    public int selectTarget() {
        List<int[]> candidate = assignCandidateNumberEachHand();
        int result = 0;
        int min = DECK_COUNT;
        for (int[] i : candidate) {
            if (i.length < min && i.length != 0) {
                result = candidate.indexOf(i);
                min = i.length;
            }
        }
        return result;
    }

    @Override
    public int declareNumber(int target) {
        int[] array = assignCandidateNumberEachHand().get(target);
        return array[new Random().nextInt(array.length)];
    }


}
