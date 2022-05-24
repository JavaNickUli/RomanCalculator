package com.nikuli;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().toUpperCase();
            try {
                System.out.println(calc(input));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String calc(String input) throws Exception {
        int result;
        boolean roman;
        String operator;
        String[] expression;
        int operand1, operand2;
        input = checkLength(input);
        roman = checkNumeric(input);
        operator = checkOperator(input);
        expression = checkExpression(input, operator);
        operand1 = stringToInt(expression[0], roman);
        operand2 = stringToInt(expression[1], roman);
        result = calculation(operand1, operand2, operator);
        return output(result, roman);
    }

    static String checkLength(String input) throws Exception {
        if (input == null) {
            throw new Exception();
        }
        input = input.trim();
        if (input.length() < 1) {
            throw new Exception();
        }
        return input;
    }

    static boolean checkNumeric(String input) throws Exception {
        int numeric = 0;
        if (checkSymbols("" + input.charAt(0), "123456789")) numeric++;
        if (checkSymbols("" + input.charAt(0), "IVX")) numeric--;
        if (numeric == 0) throw new Exception();
        return numeric < 0;
    }

    static boolean checkSymbols(String input, String compare) {
        for (char element : input.toCharArray()) {
            int i = 0;
            for (; i < compare.length(); i++) {
                if (element == compare.charAt(i)) break;
            }
            if (i == compare.length()) {
                return false;
            }
        }
        return true;
    }

    static String checkOperator(String input) throws Exception {
        String operator = "", symbols = "+-*/";
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (char element : input.toCharArray()) {
                if (symbols.charAt(i) == element) {
                    count++;
                    operator = "" + symbols.charAt(i);
                }
            }
        }
        if (count != 1) {
            throw new Exception();
        }
        return operator;
    }

    static String[] checkExpression(String input, String operator) throws Exception {
        String[] operand = new String[2];
        int indexPos = input.indexOf(operator);
        operand[0] = input.substring(0, indexPos);
        operand[1] = input.substring(indexPos + 1);
        checkLength(operand[0]);
        checkLength(operand[1]);
        return operand;
    }

    static int stringToInt(String operand, boolean roman) throws Exception {
        operand = operand.trim();
        if (roman) {
            switch (operand) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
                default:
                    throw new Exception();
            }
        } else {
            switch (operand) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
                case "8":
                    return 8;
                case "9":
                    return 9;
                case "10":
                    return 10;
                default:
                    throw new Exception();
            }
        }
    }

    static int calculation(int operand1, int operand2, String operator) {
        int result = 0;
        switch (operator) {
            case "+": {
                result = operand1 + operand2;
                break;
            }
            case "-": {
                result = operand1 - operand2;
                break;
            }
            case "*": {
                result = operand1 * operand2;
                break;
            }
            case "/": {
                result = operand1 / operand2;
                break;
            }
        }
        return result;
    }

    static String output(int result, boolean roman) throws Exception {
        String output = "";
        if (roman) {
            if (result > 0) {
                output += decimalToRoman(result);
            } else throw new Exception();
        } else output += result;
        return output;
    }

    static String decimalToRoman(int number) {
        String string = "";
        for (int i = 0; i < 4; i++) {
            int roman = number % 10;
            number = number / 10;
            string = valueOfDecimal(roman, i * 2) + string;
        }
        return string;
    }

    static String valueOfDecimal(int number, int depth) {
        String romanSymbol = "IVXLCDM  ";
        String string = "";
        switch (number) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
                if (number > 4) {
                    string = string + romanSymbol.charAt(depth + 1);
                    number = number - 5;
                }
                for (int i = 0; i < number; i++) {
                    string = string + romanSymbol.charAt(depth);
                }
                break;
            case 4:
                string = string + romanSymbol.charAt(depth) + romanSymbol.charAt(depth + 1);
                break;
            case 9:
                string = string + romanSymbol.charAt(depth) + romanSymbol.charAt(depth + 2);
                break;
        }
        return string;
    }
}
