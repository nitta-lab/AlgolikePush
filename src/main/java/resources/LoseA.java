package resources;

import java.util.List;

import values.Card;

public class LoseA {
	//削除
    //boolean value;
    
    //追加
    HandsA handsA;
    public LoseA(HandsA handsA) {
    	this.handsA = handsA;
    }
    

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

    public boolean getValue() {
        //変更
    	List<Card> listA = handsA.getValue();
        boolean result = true;
        
        for (Card card : listA) {
            if (!card.isFace()) {
                result = false;
                break;
            }
        }
        return result;

    }
}