package com.company;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        startCalc();

        while (true) {
            System.out.println("Input: ");
            String line = scanner.nextLine();

            if (line.equals("exit")) {
                exitCalc();
                break;
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

        scanner.close();
    }

    private static void startCalc() {
        System.out.println("Добро пожаловать в Калькулятор 1.1, он работает только с арабскими и римскими цифрами от 1 до 10");
        System.out.println("Обладает довольно скудным функционалом и может предложить только следующие операции:");
        System.out.println("Сложение(+), Вычитание(-), Умножение(*), Деление(/)");
        System.out.println("Если Вы хотите покинуть программу, введите 'exit'");
    }

    private static void exitCalc() {
        System.out.println("До скорой встречи!");
    }
}