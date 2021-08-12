package geekbrains.lesson_6;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Для создания окна используем стандартную библиотеку Swing.
 *
 * Чтобы всё заработало, класс GameWindow наследуем от JFrame.
 */
public class GameWindow extends JFrame {

    // окно игры
    private static GameWindow game_window;

    // время последнего кадра
    private static long last_frame_time;

    // изображения
    private static Image background;
    private static Image game_over;
    private static Image space_invader;

    // координаты захватчика
    private static float space_invader_left = 200;
    private static float space_invader_top = -100;
    // скорость движения захватчика
    private static float space_invader_v = 200;

    // очки
    private static int score = 0;

    public static void main(String[] args) throws IOException {

        // подгружаем изображения
        background = ImageIO.read(GameWindow.class.getResourceAsStream("img/background.jpg"));
        game_over = ImageIO.read(GameWindow.class.getResourceAsStream("img/game_over.png"));
        space_invader = ImageIO.read(GameWindow.class.getResourceAsStream("img/space_invader.png"));

        // создаем инстанс класса, сохраняем ссылку на него в переменной класса
        game_window = new GameWindow();

        // настраиваем окно
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // при закрытии окна программа завершается
        game_window.setLocation(200, 100); // начальная позиция окна
        game_window.setSize(906, 478); // размеры окна
        game_window.setResizable(false); // запрет на изменение размера окна

        // фиксируем время первого кадра
        last_frame_time = System.nanoTime();

        // создаём панель и добавляем её на окно
        GameField game_field = new GameField();
        // добавляем обработку кликов мыши
        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // получаем координаты клика
                int x = e.getX();
                int y = e.getY();

                // определяем правую и нижнюю границы захватчика
                float space_invader_right = space_invader_left + space_invader.getWidth(null);
                float space_invader_bottom = space_invader_top + space_invader.getHeight(null);

                // проверяем, попали ли мы по захватчику
                if (space_invader_left <= x && x <= space_invader_right && space_invader_top <= y && y <= space_invader_bottom) {
                    space_invader_top = -100;
                    space_invader_left = (int)(Math.random() * (game_field.getWidth() - space_invader.getWidth(null)));
                    space_invader_v += 20;
                    score++;
                    game_window.setTitle("Score " + score);
                }
            }
        });

        game_window.add(game_field);

        // делаем окно видимым (по умолчанию, оно невидимо)
        game_window.setVisible(true);
    }

    /**
     * Метод, необходимый для того, чтобы мы могли рисовать.
     *
     * @param  G
     */
    private static void onRepaint(Graphics G) {

        // текущее время в наносекундах
        long current_time = System.nanoTime();

        // определяем время между кадрами и переводим результат из наносекунд в секунды
        float delta_time = (current_time - last_frame_time) * 0.000000001f;

        // обновляем время предыдущего кадра
        last_frame_time = current_time;

        // рисуем фон
        G.drawImage(background, 0, 0, null);

        // рисуем захватчика
        space_invader_top = space_invader_top + space_invader_v * delta_time;
        G.drawImage(space_invader, (int)space_invader_left, (int)space_invader_top, null);

        // если захватчик добрался до нижнего края окна - игра завершена
        if (space_invader_top > game_window.getHeight()) {
            G.drawImage(game_over, 500, 40, null);
        }
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

            // метод paintComponent() вызывается не постоянно, а только тогда, когда требуется
            // отрисовать панель, что может происходить довольно редко. Делаем так, чтобы это
            // происходило как можно чаще
            repaint();
        }
    }
}
