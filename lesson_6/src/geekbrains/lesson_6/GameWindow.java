package geekbrains.lesson_6;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private static GameWindow GameWindow;

    public static void main(String[] args) {
        GameWindow = new GameWindow();
        GameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GameWindow.setLocation(200, 100);
        GameWindow.setSize(906, 478);
        GameWindow.setResizable(false);
        GameField GameField = new GameField();
        GameWindow.add(GameField);
        GameWindow.setVisible(true);
    }

    private static void onRepaint(Graphics G) {
        // G.fillOval(10, 10, 200, 100);
        G.drawLine(40, 40, 300, 200);
    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics G) {
            super.paintComponent(G);
            onRepaint(G);
        }
    }
}
