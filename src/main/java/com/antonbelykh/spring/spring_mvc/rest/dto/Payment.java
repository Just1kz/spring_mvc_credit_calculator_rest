package com.antonbelykh.spring.spring_mvc.rest.dto;

public class Payment {

    private int id;

    private int  month;

    private double  debtBeforePayment;

    private double percentPayment;

    private double debtPayment;

    private double payment;

    private double debtAfterPayment;

    public Payment() {
    }

    public Payment(int id, int month, double debtBeforePayment, double percentPayment, double debtPayment, double payment, double debtAfterPayment) {
        this.id = id;
        this.month = month;
        this.debtBeforePayment = debtBeforePayment;
        this.percentPayment = percentPayment;
        this.debtPayment = debtPayment;
        this.payment = payment;
        this.debtAfterPayment = debtAfterPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getDebtBeforePayment() {
        return debtBeforePayment;
    }

    public void setDebtBeforePayment(double debtBeforePayment) {
        this.debtBeforePayment = debtBeforePayment;
    }

    public double getPercentPayment() {
        return percentPayment;
    }

    public void setPercentPayment(double percentPayment) {
        this.percentPayment = percentPayment;
    }

    public double getDebtPayment() {
        return debtPayment;
    }

    public void setDebtPayment(double debtPayment) {
        this.debtPayment = debtPayment;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getDebtAfterPayment() {
        return debtAfterPayment;
    }

    public void setDebtAfterPayment(double debtAfterPayment) {
        this.debtAfterPayment = debtAfterPayment;
    }
}
