package com.hillel.artemjev.cashflow.service;

import java.util.*;


public class PaymentService {
    private List<Payment> payments;

    public PaymentService() {
        this.payments = new LinkedList<>();
    }

    public void add(Payment payment) {
        payments.add(payment);
    }

    public double calcOverallResult() {
        return payments.stream()
                .map(Payment::getSum)
                .reduce(0.0, (agg, sum) -> agg + sum);
    }

    public double calcIncome() {
        return payments.stream()
                .map(Payment::getSum)
                .filter(sum -> sum > 0)
                .reduce(0.0, (agg, sum) -> agg + sum);
    }

    public double calcExpense() {
        return payments.stream()
                .map(Payment::getSum)
                .filter(sum -> sum < 0)
                .reduce(0.0, (agg, sum) -> agg + sum);
    }

    public Map<String, Double> getIncomeMapPurposeSum() {
        Map<String, Double> incomes = new HashMap<>();
        payments.stream()
                .filter(payment -> payment.getSum() >= 0)
                .forEach(payment -> {
                    incomes.put(payment.getPurpose(),
                            incomes.containsKey(payment.getPurpose()) ?
                                    incomes.get(payment.getPurpose()) + payment.getSum() : payment.getSum());
                });
        return incomes;
    }

    public Map<String, Double> getExpenseMapPurposeSum() {
        Map<String, Double> expenses = new HashMap<>();
        payments.stream()
                .filter(payment -> payment.getSum() < 0)
                .forEach(payment -> {
                    expenses.put(payment.getPurpose(),
                            expenses.containsKey(payment.getPurpose()) ?
                                    expenses.get(payment.getPurpose()) + payment.getSum() : payment.getSum());
                });
        return expenses;
    }
}
