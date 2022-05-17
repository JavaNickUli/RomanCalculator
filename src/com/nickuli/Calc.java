package com.nikuli.test;

import javax.swing.*;

public class Calc {
    public static void main(String[] args) {
        while (true) {
            calc(JOptionPane.showInputDialog(null, "Введите вычисляемое выражение.",
                    "Римский калькулятор", JOptionPane.PLAIN_MESSAGE));
        }
    }

    public static void calc(String string) {
        if (string == null) {System.exit(0);}
        if (string.equals("")) {
            outputErrors("Вы не ввели число.");
        }
    }
    static void outputErrors(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
    }
}
