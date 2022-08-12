import views.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(640, 480);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
