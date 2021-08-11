package geekbrains.lesson_3;

import java.util.Scanner;

public class Main {

    private static final Scanner Scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Ваша задача угадать число.");

        int[] levels = {10, 20, 30};

        for (int i = 0, currLevel = 1; i < levels.length; i++, currLevel++) {
            System.out.println("Уровень " + currLevel + ": угадайте число от 0 до " + levels[i]);
            playLevel(levels[i]);
        }

        Scanner.close();
    }

    private static void playLevel(int range) {
        // генерируем число в заданном диапазоне
        int number = (int)(Math.random() * range);

        // счетчик попыток
        int attempts = 0;

        while (true) {
            int inputNumber = Scanner.nextInt();
            attempts++;

            if (inputNumber == number) {
                System.out.println("Вы угадали. Потребовалось попыток: " + attempts);
                break;
            } else if (inputNumber > number) {
                System.out.println("Загаданное число меньше");
            } else {
                System.out.println("Загаданное число больше");
            }
        }
    }
}
