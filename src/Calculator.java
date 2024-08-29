package com.company;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean restart = true;

        while (restart) {
            startCalc();
            performCalculation(scanner);

            System.out.println("Хотите перезапустить программу? (да/нет)");
            String restartInput = scanner.nextLine().toLowerCase();
            restart = restartInput.equals("да");
        }

        scanner.close();
        exitCalc();
    }

    private static void startCalc() {
        System.out.println("Добро пожаловать в Калькулятор 1.1, он работает только с арабскими и римскими цифрами от 1 до 10");
        System.out.println("Обладает довольно скудным функционалом и может предложить только следующие операции:");
        System.out.println("Сложение(+), Вычитание(-), Умножение(*), Деление(/)");
        System.out.println("Если Вы хотите покинуть программу, введите 'exit'");
    }

    private static void performCalculation(Scanner scanner) {
        while (true) {
            System.out.println("Input: ");
            String line = scanner.nextLine().trim();

            if (line.equals("exit")) {
                break;
            }

            if (!line.matches("\\d+ [+\\-*/] \\d+") && !line.matches("[IVXLCDM]+ [+\\-*/] [IVXLCDM]+")) {
                System.out.println("Неверный формат ввода. Используйте формат 'число оператор число' с пробелами.");
                continue;
            }

            try {
                String[] symbols = line.split(" ");
                if (symbols.length != 3) throw new Exception("Что-то пошло не так, попробуйте еще раз");

                Number firstNumber = NumberService.parseAndValidate(symbols[0]);
                Number secondNumber = NumberService.parseAndValidate(symbols[2], firstNumber.getType());
                String result = ActionService.calculate(firstNumber, secondNumber, symbols[1]);
                System.out.println("Output: \n" + result);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void exitCalc() {
        System.out.println("До скорой встречи!");
    }
}