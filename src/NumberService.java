package com.company;

import java.util.TreeMap;

public class NumberService {
    private static final TreeMap<Integer, String> romanString = new TreeMap<>();

    static {
        romanString.put(1, "I");
        romanString.put(4, "IV");
        romanString.put(5, "V");
        romanString.put(9, "IX");
        romanString.put(10, "X");
        romanString.put(40, "XL");
        romanString.put(50, "L");
        romanString.put(90, "XC");
        romanString.put(100, "C");
    }

    public static Number parseAndValidate(String symbol) throws Exception {
        int value;
        NumberType type;

        try {
            value = Integer.parseInt(symbol);
            type = NumberType.ARABIC;
        } catch (NumberFormatException e) {
            value = toArabicNumber(symbol);
            type = NumberType.ROMAN;
        }

        if (value < 1 || value > 10) {
            throw new Exception("Неподходящее значение числа(ел), используйте числа от 1 до 10 включительно");
        }

        return new Number(value, type);
    }

    public static Number parseAndValidate(String symbol, NumberType type) throws Exception {
        Number number = parseAndValidate(symbol);
        if (number.getType() != type) {
            throw new Exception("Числа разных типов, используйте один тип вводных значений");
        }

        return number;
    }

    private static int letterToNumber(char letter) {
        switch (letter) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            default: return -1;
        }
    }

    public static String toRomanNumber(int number) {
        int i = romanString.floorKey(number);
        if (number == i) {
            return romanString.get(number);
        }
        return romanString.get(i) + toRomanNumber(number - i);
    }

    public static int toArabicNumber(String roman) throws Exception {
        int result = 0;
        int i = 0;

        while (i < roman.length()) {
            char letter = roman.charAt(i);
            int num = letterToNumber(letter);

            if (num < 0) {
                throw new Exception("Неверный римский символ");
            }

            i++;
            if (i == roman.length()) {
                result += num;
            } else {
                int nextNum = letterToNumber(roman.charAt(i));
                if (nextNum > num) {
                    result += (nextNum - num);
                    i++;
                } else {
                    result += num;
                }
            }
        }

        return result;
    }
}