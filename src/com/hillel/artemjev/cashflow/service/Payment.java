package com.hillel.artemjev.cashflow.service;

import java.util.Objects;

public class Payment {
    private double sum;
    private String purpose;

    public Payment(double sum, String purpose) {
        this.sum = sum;
        this.purpose = purpose;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.getSum(), getSum()) == 0 &&
                Objects.equals(getPurpose(), payment.getPurpose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSum(), getPurpose());
    }

    @Override
    public String toString() {
        return "Payment{" +
                "sum=" + sum +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
