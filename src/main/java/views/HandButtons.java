package views;

import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 手札の情報をボタンで扱うためのクラス
 */
class HandButtons extends ArrayList<CardButton> {
    public void addListeners(ActionListener al) {
        for (CardButton hb : this) {
            hb.addActionListener(al);
        }
    }

    public void setEnableButtons(boolean enableButtons) {
        for (CardButton hb : this) {
            hb.setEnabled(enableButtons);
        }
    }

    public void removeButtonListeners() {
        for (CardButton my : this) {
            for (ActionListener al : my.getActionListeners()) {
                my.removeActionListener(al);
            }
        }
    }

}