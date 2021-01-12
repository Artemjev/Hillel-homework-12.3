package com.hillel.artemjev.cashflow.menu;

import com.hillel.artemjev.cashflow.service.PaymentParser;
import com.hillel.artemjev.cashflow.service.PaymentService;

import java.util.Map;
import java.util.Scanner;

public class Menu {
    private PaymentService paymentService;
    private PaymentParser paymentParser;
    private Scanner sc;

    public Menu(PaymentService paymentService, PaymentParser paymentParser, Scanner sc) {
        this.paymentService = paymentService;
        this.paymentParser = paymentParser;
        this.sc = sc;
    }

    public void run() {
        System.out.println("Введите информацию о расходах и доходах:");
        while (true) {
            String enteredString = sc.nextLine();
            if (enteredString.equalsIgnoreCase("END")) break;

            if (!paymentParser.validatePaymentString(enteredString)) {
                System.out.println("Не корректный формат ввода. Пример: \"-888.8 статья расхода\" или " +
                        "\"+888.8 статья ддохода\". Повторите ввод:");
            } else {
                paymentService.add(paymentParser.parse(enteredString));
            }
        }

        double result = paymentService.calcOverallResult();
        if (result >= 0) {
            System.out.println("\nОбщий доход: " + result);
        } else {
            System.out.println("\nОбщий убыток: " + result);
        }

        System.out.println("\nРасходы состовляют " + Math.abs(paymentService.calcExpense()) + " из которых:");

        for (Map.Entry<String, Double> entry : paymentService.getExpenseMapPurposeSum().entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + Math.abs(entry.getValue()));
        }

        System.out.println("\nДоходы состовляют " + paymentService.calcIncome() + " из которых:");
        for (Map.Entry<String, Double> entry : paymentService.getIncomeMapPurposeSum().entrySet()) {
            System.out.println(" -" + entry.getKey() + ": " + entry.getValue());
        }
    }
}



