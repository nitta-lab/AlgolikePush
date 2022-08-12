package views;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static views.Constants.*;

public class CardButton extends JButton {
    private Status status;
    final int UNSELECTED_THICKNESS = 1;
    final int SELECTED_THICKNESS = 4;

    public CardButton(String text) {
        this.setText(text);
        this.setBorder(new LineBorder(UNSELECTED_COLOR, UNSELECTED_THICKNESS, true));
        this.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        this.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 14));
    }

    //選択状態の切り替え
    public void setEnabledSelection(boolean enabledSelection) {
        if (enabledSelection) {
            this.setBorder(new LineBorder(SELECTED_COLOR, SELECTED_THICKNESS, true));
        } else {
            this.setBorder(new LineBorder(UNSELECTED_COLOR, UNSELECTED_THICKNESS, true));
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        switch (status) {
            case OPEN:
                this.setBackground(OPEN_COLOR);
                this.setForeground(CLOSED_COLOR);
                break;
            case CLOSED:
                this.setBackground(CLOSED_COLOR);
                this.setForeground(OPEN_COLOR);
                break;
            case MY_CLOSED:
                this.setContentAreaFilled(false);
                this.setFocusPainted(false); // used for demonstration
                this.setForeground(OPEN_COLOR);
                break;
        }
        this.status = status;
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                OPEN_COLOR,
                new Point(0, getHeight()),
                CLOSED_COLOR));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }

    public enum Status {
        OPEN,       //開示されている
        CLOSED,     //開示されていない
        MY_CLOSED   //開示されていない(ただし自分専用)
    }
}
