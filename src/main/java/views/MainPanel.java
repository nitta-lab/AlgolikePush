package views;

import controls.AbstractGameState;
import controls.BotIntelligence;
import controls.PhaseController;
import controls.TurnBot;
import interfaces.IGameView;
import resources.Algo;
import values.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static views.Constants.*;

public class MainPanel extends JPanel implements IGameView {
    PhaseController phaseController;
    JPanel myHandButtonsPanel;
    JPanel myHandAttackerPanel;
    JPanel myPanel;
    JPanel opponentButtonsPanel;
    JPanel opponentAttackerPanel;
    JPanel opponentPanel;
    private HandButtons myHandButtons;
    private HandButtons opponentHandButtons;
    private JPanel deckButtonPanel;
    List<ActivateListener> activateListeners = new ArrayList<>();

    public MainPanel(Algo algo) {
        super(new BorderLayout());
        myHandButtons = new HandButtons();
        opponentHandButtons = new HandButtons();

        deckButtonPanel = new JPanel();
        myHandButtonsPanel = new JPanel();
        opponentButtonsPanel = new JPanel();

        myHandAttackerPanel = new JPanel();
        myPanel = new JPanel();

        opponentAttackerPanel = new JPanel();
        opponentPanel = new JPanel();

        phaseController = new PhaseController(algo, this);

        myPanel.add(myHandAttackerPanel);
        myPanel.add(myHandButtonsPanel);

        opponentPanel.add(opponentAttackerPanel);
        opponentPanel.add(opponentButtonsPanel);

        add(deckButtonPanel, BorderLayout.WEST);
        add(myPanel, BorderLayout.SOUTH);
        add(opponentPanel, BorderLayout.NORTH);

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {
                phaseController.startGame();
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        updateOpponentHandButtons();
        repaint();

        this.addActivateListener(new ActivateListener() {
            @Override
            public void onActivate() {
                phaseController.startGame();
            }
        });
    }

    public void addActivateListener(ActivateListener activateListener) {
        activateListeners.add(activateListener);
    }

    public void activate() {
        for (ActivateListener al : activateListeners) al.onActivate();
    }

    void updateMyHandButtons() {
        /**
         * 自分の手札のボタンにリスナーを追加する処理
         */
        myHandButtons.removeButtonListeners();
        myHandButtons.setEnableButtons(true);
        myHandButtons.addListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardButton sourceButton = ((CardButton) e.getSource());
                boolean isOpened = sourceButton.getStatus() == CardButton.Status.OPEN;
                if (isOpened) {
                    JOptionPane.showMessageDialog(null, "裏のカードを選んでください. ", "Warn",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                for (CardButton my : MainPanel.this.myHandButtons) my.setEnabledSelection(false);
                sourceButton.setEnabledSelection(true);
                int option = JOptionPane.showConfirmDialog(null, "このカードを使ってアタックをしますか?", "confirmation", 2);

                if (option == JOptionPane.YES_OPTION) {
                    int attacker = myHandButtons.indexOf(sourceButton);
                    phaseController.setSelection(attacker);
                } else {
                    sourceButton.setEnabledSelection(false);
                }

            }
        });
    }

    void updateOpponentHandButtons() {
        /**
         * 相手の手札を選択する処理をリスナーに追加する
         */
        opponentHandButtons.removeButtonListeners();
        opponentHandButtons.addListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!phaseController.isDecidedAttacker()) {
                    JOptionPane.showMessageDialog(null, "あなたの手札からアタックに使用するカードを選んでください. ", "Warn",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                CardButton sourceButton = ((CardButton) e.getSource());
                int index = opponentHandButtons.indexOf(sourceButton);
                sourceButton.setEnabledSelection(true);
                //相手のカードを選択したときに確認用ダイアログを出す
                int option = JOptionPane.showConfirmDialog(null, "このカードを選びますか?", "confirmation", 2);
                if (option == JOptionPane.YES_OPTION) {
                    phaseController.setTarget(index);
                } else {
                    sourceButton.setEnabledSelection(false);
                }
            }
        });
    }

    void paintDrawCard(AbstractGameState abstractGameState) {
        Card deckTopCard = abstractGameState.getTopCard();

        if (!abstractGameState.isDeckLess()) {    //デッキが存在する場合にデッキトップのカードを表示する処理
            CardButton cardButton = new CardButton(((Integer)deckTopCard.getNumber()).toString());
            cardButton.setBounds(0, 100, CARD_WIDTH, CARD_HEIGHT);
            if (abstractGameState.isATurn()) {
                myHandAttackerPanel.add(cardButton);
            } else {
                cardButton.setText(CLOSED_SYMBOL);
                opponentAttackerPanel.add(cardButton);
            }
            if (abstractGameState.getDeckNumber() == 1) {
                deckButtonPanel.removeAll();
            }
        } else {
            updateMyHandButtons();
        }

        validate();
        repaint();
    }

    @Override
    public void onStartPlayerTurn(AbstractGameState abstractGameState) {
        JOptionPane.showMessageDialog(null, "あなたのターンです。");
        String selectText = !abstractGameState.isDeckLess()
                ? "あなたは数字\"" + abstractGameState.getTopCard().getNumber() + "\"のカードをドローしました。"
                : "アタックに使用するカードを手札から選んでください。";

        paintDrawCard(abstractGameState);
        if (!abstractGameState.isDeckLess()) {
            selectText = "あなたは数字\"" + abstractGameState.getTopCard().getNumber() + "\"のカードをドローしました。";
            phaseController.setSelection(0);
        } else {
            selectText = "アタックに使用するカードを手札から選んでください。";
        }
        JOptionPane.showMessageDialog(null, selectText);

    }

    @Override
    public void onDecidedSelection(int attacker) {
    }

    @Override
    public void onDecidedTarget(int target) {
        String[] optionsToChoose = new String[DECK_COUNT];
        for (int i = 0; i < optionsToChoose.length; i++) optionsToChoose[i] = String.valueOf(i);
        String getDeclaredNumber = (String) JOptionPane.showInputDialog(
                null,
                "このカードの数字を宣言してください。",
                "Declare Number",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsToChoose,
                optionsToChoose[0]);

        if (getDeclaredNumber != null) {   //数字を宣言して、承認したとき
            int g = parseInt(getDeclaredNumber);
            phaseController.setDeclaration(g);
        } else {
            opponentHandButtons.get(target).setEnabledSelection(false);
        }
    }

    @Override
    public void onFinishedPlayerAttack(int guess, boolean isSucceed) {
        String resultMessage = "あなたのアタックは";
        resultMessage += isSucceed ? "成功しました。" : "失敗しました。";
        resultMessage += "(宣言した値：" + guess + ")";
        JOptionPane.showMessageDialog(null, resultMessage);
    }

    @Override
    public void onStartBotAttack(TurnBot turnBot) {
        BotIntelligence bot = new BotIntelligence(turnBot);
        JOptionPane.showMessageDialog(null, "CPUのターンです。");
        String selectText = "";
        int atk = 0;
        if (!turnBot.isDeckLess()) { //デッキにカードが存在するとき
            paintDrawCard(turnBot);//デッキから引いたカードを描画する
            selectText = "CPUはカードをドローしました。";
        } else {
            atk = bot.selectAttacker();
            opponentHandButtons.get(atk).setEnabledSelection(true);
            selectText = "CPUはアタックに使用するカードを選びました。";
        }
        JOptionPane.showMessageDialog(null, selectText);

        String targetText = "";
        int tar = bot.selectTarget();
        myHandButtons.get(tar).setEnabledSelection(true);
        JOptionPane.showMessageDialog(null, "CPUはこのカードを対象にしました。");


        int dec = bot.declareNumber(tar);
        JOptionPane.showMessageDialog(null, "CPUは\"" + dec + "\"を宣言しました。");

        phaseController.botAttack(dec, atk, tar);

    }

    @Override
    public void onFinishedBotAttack(int guess, boolean isSucceed) {
        String resultMessage = "CPUのアタックは";
        resultMessage += isSucceed ? "成功しました。" : "失敗しました。";
        resultMessage += "(宣言した値：" + guess + ")";
        JOptionPane.showMessageDialog(null, resultMessage);

    }

    @Override
    public void repaintBoard(AbstractGameState abstractGameState) {
        List<Card> myHands = abstractGameState.getMyHands();
        List<Card> opponentHands = abstractGameState.getOpponentHands();

        /**
         * 初期化処理(する必要があるのかどうかは知らない)
         */
        deckButtonPanel.removeAll();
        myHandButtonsPanel.removeAll();//
        myHandButtons.clear();
        myHandAttackerPanel.removeAll();
        opponentAttackerPanel.removeAll();
        opponentButtonsPanel.removeAll();//
        opponentHandButtons.clear();

        if (!abstractGameState.isDeckLess()) {        //デッキが存在する場合
            JButton cardButton = new JButton("deck");
            cardButton.setPreferredSize(new Dimension(CARD_HEIGHT, CARD_WIDTH));
            deckButtonPanel.add(cardButton);
        }
        for (Card i : myHands) {
            CardButton cardButton = new CardButton(((Integer)i.getNumber()).toString());
            cardButton.setStatus(i.isFace() ? CardButton.Status.OPEN : CardButton.Status.MY_CLOSED);
            myHandButtons.add(cardButton);
            myHandButtonsPanel.add(cardButton);
        }
        //ここまでが自分のカードに関する処理

        /**
         *相手のカードに関する処理
         */
        for (Card i : opponentHands) {
            CardButton cardButton = new CardButton(i.isFace() ? ((Integer)i.getNumber()).toString() : CLOSED_SYMBOL);
            cardButton.setStatus(i.isFace() ? CardButton.Status.OPEN : CardButton.Status.CLOSED);
            cardButton.setEnabled(!i.isFace());
            opponentHandButtons.add(cardButton);
            opponentButtonsPanel.add(cardButton, 0);//見た目の順序が逆になるように,0番目に挿入
        }

        updateOpponentHandButtons();
        //ここまでが相手のカードに関する処理
        validate();
        repaint();
    }

    @Override
    public void onFinishedGame(AbstractGameState abstractGameState, boolean isLoseA) {
        this.repaintBoard(abstractGameState);
        myHandButtons.setEnableButtons(false);
        opponentHandButtons.setEnableButtons(false);
        if (isLoseA) {
            JOptionPane.showMessageDialog(null, "CPUが勝利しました。");
        } else {
            JOptionPane.showMessageDialog(null, "あなたが勝利しました。");
        }
    }
}
