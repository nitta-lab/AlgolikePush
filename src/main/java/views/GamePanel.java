package views;

import resources.Algo;

import javax.swing.*;
import java.util.List;

public class GamePanel extends JPanel {
    List<JButton> selectableButtons;

    public GamePanel(Algo algo) {

    }

    public void setSelectableButtons(Area area) {

    }

    public enum Area {
        HANDS_A,
        HANDS_B,
        DECK
    }

    public enum Step {
        PREPARATION,
        SELECTION,
        DECLARATION,
        CONFIRM
    }
}
