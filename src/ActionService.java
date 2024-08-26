package com.company;

public class ActionService {
    public static String calculate(Number first, Number second, String action) throws Exception {
        int result;

        switch (action) {
            case "+":
                result = first.getValue() + second.getValue();
                break;
            case "-":
                result = first.getValue() - second.getValue();
                break;
            case "*":
                result = first.getValue() * second.getValue();
                break;
            case "/":
                if (second.getValue() == 0) {
                    throw new Exception("Деление на ноль невозможно");
                }
                result = first.getValue() / second.getValue();
                break;
            default:
                throw new Exception("Неправильно введен символ операции, используйте только +, -, *, /");
        }

        if (first.getType() == NumberType.ROMAN) {
            return NumberService.toRomanNumber(result);
        } else {
            return String.valueOf(result);
        }
    }
}