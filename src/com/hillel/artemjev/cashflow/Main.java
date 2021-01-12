package com.hillel.artemjev.cashflow;

import com.hillel.artemjev.cashflow.menu.Menu;
import com.hillel.artemjev.cashflow.service.PaymentParser;
import com.hillel.artemjev.cashflow.service.PaymentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PaymentService paymentService = new PaymentService();
        PaymentParser paymentParser = new PaymentParser();

        // для удобства проверки, чтобы меньше вводить!
        paymentService.add(paymentParser.parse("-700 закупка аппаратуры"));
        paymentService.add(paymentParser.parse("-150 услуги почты"));
        paymentService.add(paymentParser.parse("+2000 продажа товара"));
        paymentService.add(paymentParser.parse("-200 услуги почты"));
        paymentService.add(paymentParser.parse("-100 неплановые расходы"));
        paymentService.add(paymentParser.parse("+7525 продажа товара"));

        Menu menu = new Menu(paymentService, paymentParser, sc);
        menu.run();
    }
}

