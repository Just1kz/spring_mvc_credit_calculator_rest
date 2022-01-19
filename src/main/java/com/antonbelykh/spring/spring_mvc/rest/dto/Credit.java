package com.antonbelykh.spring.spring_mvc.rest.dto;

import java.util.List;

public class Credit {

    private int id;

    private int periodCredit;

    private double amount;

    private double costPercentage;

    private String status;

    private List<Payment> paymentList;

    public Credit() {
    }

    public Credit(int id, int periodCredit, double amount, double costPercentage, String status) {
        this.id = id;
        this.periodCredit = periodCredit;
        this.amount = amount;
        this.costPercentage = costPercentage;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPeriodCredit() {
        return periodCredit;
    }

    public void setPeriodCredit(int periodCredit) {
            this.periodCredit = periodCredit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCostPercentage() {
        return costPercentage;
    }

    public void setCostPercentage(double costPercentage) {
        this.costPercentage = costPercentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}
