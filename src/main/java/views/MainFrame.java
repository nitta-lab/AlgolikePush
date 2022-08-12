package views;

import controls.PhaseController;
import resources.Algo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Algolike");
        Algo algo = new Algo();
        algo.distributeHands();
        MainPanel mainPanel = new MainPanel(algo);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(mainPanel, BorderLayout.CENTER);
        this.pack();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                mainPanel.activate();
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
