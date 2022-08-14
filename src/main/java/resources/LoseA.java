package resources;

import values.Card;

public class LoseA {
	//7.LoseA クラスのプレイヤーの勝敗結果を保存するためのフィールド boolean value を削除する．
//    boolean value;
    // 2.LoseA クラスに，handsA クラスのインスタンスを参照するフィールド HandsA handsA を追加する．
    HandsA handsA;

    // 3.LoseA クラスに，HandsA クラスのインスタンスを引数に取るコンストラクタを追加する．
    public LoseA(HandsA handsA) {
    	this.handsA = handsA;
	}
    //11.LoseA クラスの void updateHandsA(List<Card> handsA) メソッドを削除する．
//    public void updateHandsA(List<Card> handsA) {
//        boolean result = true;
//        for (Card card : handsA) {
//            if (!card.isFace()) {
//                result = false;
//                break;
//            }
//        }
//        value = result;
//    }
	//9.LoseA クラスの getValue() メソッドを HandsA クラスの getValue() メソッドを呼び出すように書き換える．
//    public boolean getValue() {
//        return value;
//    }
    //10.LoseA クラスの updateHandsA() メソッド内で行っていた，プレイヤーの手札の情報をもとに勝敗判定を行う処理を，getValue() メソッドに移動し，判定結果を getValue() メソッドの戻り値として返すようにする．
    public boolean getValue(){
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