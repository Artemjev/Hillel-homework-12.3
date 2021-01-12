package com.hillel.artemjev.cashflow.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentParser {
    private static final String PAYMENT_REGEX_PATTERN = "((?:\\+|-)\\d+) (.+)";
    private Pattern pattern;

    public PaymentParser() {
        this.pattern = Pattern.compile(PAYMENT_REGEX_PATTERN);
    }

    public Payment parse(String paymentStr) {
        Payment payment = null;
        if (validatePaymentString(paymentStr)) {
            Matcher matcher = pattern.matcher(paymentStr);
            if ((matcher.find())) {
                Double sum = Double.valueOf(matcher.group(1));
                String purpose = matcher.group(2);
                payment = new Payment(sum, purpose);
            }
        }
        return payment;
    }

    public boolean validatePaymentString(String paymentStr) {
        return paymentStr.matches(PAYMENT_REGEX_PATTERN);
    }
}
