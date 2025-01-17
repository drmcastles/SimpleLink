package ru.mifi.stepan.shortlink.view.utils;

import java.util.Scanner;

public class ConsoleUtil {


    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int getLimits() {
        System.out.println("Введите лимит переходов");
        try {
            int result = Integer.valueOf(readFromConsole());
            if (result > 0 && result < 100) {
                return result;
            }
        } catch (NumberFormatException e) {
            // обработка ниже так же как и в случае отсутствия ошибок
        }
        System.out.println("Необходимо ввести число от 0 до 100");
        return getLimits();
    }
}
