package com.company;

public class ActionService {
    public static String calculate(Number firstNumber, Number secondNumber, String operation) throws Exception {
        int result = switch (operation) {
            case "+" -> firstNumber.getValue() + secondNumber.getValue();
            case "-" -> firstNumber.getValue() - secondNumber.getValue();
            case "*" -> firstNumber.getValue() * secondNumber.getValue();
            case "/" -> {
                if (secondNumber.getValue() == 0) {
                    throw new Exception("Деление на ноль невозможно");
                }
                yield firstNumber.getValue() / secondNumber.getValue();
            }
            default -> throw new Exception("Неизвестная операция");
        };

        if (firstNumber.getType() == NumberType.ROMAN) {
            return NumberService.toRomanNumber(result);
        } else {
            return String.valueOf(result);
        }
    }
}