package com.nickuli;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String string = scanner.nextLine();
            try {
                checkCorrectInput(string);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
            calc(string);
        }
    }

    public static void calc(String string) {
        String[] strings = string.split(" ");
        int a = romanToDecimal(strings[0]), b = romanToDecimal(strings[2]);
        int result = 0;
        switch (strings[1].charAt(0)) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }
        try {
            checkCorrectNumber(result);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(decimalToRoman(result));
    }

    static int romanToDecimal(String string) {
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            if (i + 1 == string.length() || valueOfRoman(string.charAt(i)) >= valueOfRoman(string.charAt(i + 1))) {
                result = result + valueOfRoman(string.charAt(i));
            } else {
                result = result - valueOfRoman(string.charAt(i));
            }
        }
        return result;
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

    static void checkCorrectInput(String string) throws Exception {
        if (string.length() < 5) {
            throw new Exception();
        }
        String[] strings = string.split(" ");
        if (strings.length != 3 || strings[1].length() != 1) {
            throw new Exception();
        }
        if (checkRomanSymbols(strings[0]) && checkRomanSymbols(strings[2])) {
            throw new Exception();
        }
        if (!(strings[1].equals("+") || strings[1].equals("-") || strings[1].equals("*") || strings[1].equals("/"))) {
            throw new Exception();
        }
    }
    static void checkCorrectNumber(int number) throws Exception {
        if (number<1 || number > 3999) {
            throw new Exception();
        }
    }

    static boolean checkRomanSymbols(String string) {
        boolean check = false;
        for (int i = 0; i < string.length(); i++) {
            if (valueOfRoman(string.charAt(i)) == 0) {
                check = true;
            }
        }
        return check;
    }

    static int valueOfRoman(char symbol) {
        int result = 0;
        switch (symbol) {
            case 'I':
                result = 1;
                break;
            case 'V':
                result = 5;
                break;
            case 'X':
                result = 10;
                break;
            case 'L':
                result = 50;
                break;
            case 'C':
                result = 100;
                break;
            case 'D':
                result = 500;
                break;
            case 'M':
                result = 1000;
                break;
        }
        return result;
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
