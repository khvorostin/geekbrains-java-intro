package geekbrains.lesson_3;

import java.util.Scanner;

public class Main {

    private static Scanner Scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Ваша задача угадать число.");
        System.out.println("Укажите число, которым вы хотите ограничить диапазон:");

        int range = Scanner.nextInt();
        int number = (int)(Math.random() * range);

        System.out.println("Угадайте число от 0 до " + range);
        playLevel(range, number);
        Scanner.close();
    }

    private static void playLevel(int range, int number) {
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
