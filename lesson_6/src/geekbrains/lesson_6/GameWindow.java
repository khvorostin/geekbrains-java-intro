package geekbrains.lesson_6;

import javax.swing.*;
import java.awt.*;

/**
 * Для создания окна используем стандартную библиотеку Swing.
 *
 * Чтобы всё заработало, класс GameWindow наследуем от JFrame.
 */
public class GameWindow extends JFrame {

    private static GameWindow game_window;

    public static void main(String[] args) {
        // создаем инстанс класса, сохраняем ссылку на него в переменной класса
        game_window = new GameWindow();
        // настраиваем окно
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // при закрытии окна программа завершается
        game_window.setLocation(200, 100); // начальная позиция окна
        game_window.setSize(906, 478); // размеры окна
        game_window.setResizable(false); // запрет на изменение размера окна

        // создаём панель и добавляем её на окно
        GameField game_field = new GameField();
        game_window.add(game_field);

        game_window.setVisible(true); // делаем окно видимым (по умолчанию, оно невидимо)
    }

    /**
     * Метод, необходимый для того, чтобы мы могли рисовать.
     *
     * @param  G
     */
    private static void onRepaint(Graphics G) {
        // G.fillOval(10, 10, 200, 100);
        G.drawLine(40, 40, 300, 200);
    }

    /**
     * Рисовать мы можем на так называемых панелях, которые представленны классом JPanel.
     *
     * При отрисовке какого-либо графического компонента (например, того же JPanel), у него внутри вызывается
     * метод paintComponent(), которому как параметр передаётся объект класса Graphics, с помощью которого
     * он и рисуется.
     */
    private static class GameField extends JPanel {

        /**
         * Меняем поведение метода paintComponent(), который находится в классе JPanel, на своё поведение (так
         * называемое "динамическое замещение метода").
         *
         * @param G
         */
        @Override
        protected void paintComponent(Graphics G) {
            // отрисовываем панель (это делает метод paintComponent() класса JPanel,
            // т.е. выполняем метод из родительского класса)
            super.paintComponent(G);
            // вызываем наш метод
            onRepaint(G);
        }
    }
}
