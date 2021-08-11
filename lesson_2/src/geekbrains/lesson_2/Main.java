package geekbrains.lesson_2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Введите операцию: 1 - сложение, 2 - вычитание, 3 - умножение, 4 - деление");
        Scanner Scanner = new Scanner(System.in);
        int operation = Scanner.nextInt();

	    System.out.println("Введите первое число");
	    int a = Scanner.nextInt();

        System.out.println("Введите второе число");
        int b = Scanner.nextInt();

        int result;

        if (operation == 1) {
            result = a + b;
        } else if (operation == 2) {
            result = a - b;
        } else if (operation == 3) {
            result = a * b;
        } else {
            result = a / b;
        }

        System.out.println("Результат = " + result);
    }
}
