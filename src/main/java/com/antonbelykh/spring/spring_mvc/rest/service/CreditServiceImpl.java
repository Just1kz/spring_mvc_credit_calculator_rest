package com.antonbelykh.spring.spring_mvc.rest.service;


import com.antonbelykh.spring.spring_mvc.rest.dao.CreditDAOJdbc;
import com.antonbelykh.spring.spring_mvc.rest.dto.Credit;
import com.antonbelykh.spring.spring_mvc.rest.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    private final CreditDAOJdbc creditDAOJdbc;

    @Autowired
    public CreditServiceImpl(CreditDAOJdbc creditDAOJdbc) {
        this.creditDAOJdbc = creditDAOJdbc;
    }

    @Override
    public List<Credit> getAllCredits() {
        return creditDAOJdbc.getAllCredits();
    }

    @Override
    public Credit getCreditById(int id) {
        return creditDAOJdbc.getCreditById(id);
    }

    @Override
    public void saveOrUpdateCredit(Credit credit) {
        creditDAOJdbc.saveOrUpdateCredit(credit);
    }

    @Override
    public void deleteCreditById(int id) {
        //сначала удаление связанных столбцов других таблиц во избежания нарушения целостности
        creditDAOJdbc.deleteScheduleAfterUpdateCreditParamsByCreditId(id);
        creditDAOJdbc.deleteCreditById(id);
    }

    @Override
    public void calculateSchedulePaymentsByCreditId(int id) {
        Credit credit = getCreditById(id);
        List<Payment> schedulePayment = new ArrayList<>();
        Payment payment;
        double annuityPayment = calculateAnnuityPayment(credit);
        double everyMonthPercentRatio = credit.getCostPercentage()/100/12;

        for (int i = 0; i < credit.getPeriodCredit(); i++) {
            payment = new Payment();
            payment.setPayment(annuityPayment);

            if (i==0) {
                payment.setDebtBeforePayment(credit.getAmount());
            } else {
                int count = i -1;
                payment.setDebtBeforePayment(schedulePayment.get(count).getDebtAfterPayment());
            }
            payment.setMonth(i+1);
            payment.setPercentPayment(payment.getDebtBeforePayment() * everyMonthPercentRatio);
            payment.setDebtPayment(payment.getPayment() - payment.getPercentPayment());
            payment.setDebtAfterPayment(payment.getDebtBeforePayment() - payment.getDebtPayment());
            schedulePayment.add(payment);
        }

        for (Payment rsl : schedulePayment) {
            creditDAOJdbc.addPaymentById(credit.getId(), rsl);
        }

        credit.setStatus("Calculate is Done!");
        creditDAOJdbc.saveOrUpdateCredit(credit);
    }

    @Override
    public void deleteScheduleAfterUpdateCreditParamsByCreditId(int id) {
        creditDAOJdbc.deleteScheduleAfterUpdateCreditParamsByCreditId(id);
    }

    @Override
    public List<Payment> getPaymentsByCreditId(int id) {
        return creditDAOJdbc.getPaymentsByCreditId(id);
    }

    @Override
    public void deletePaymentsByCreditId(int id) {
        creditDAOJdbc.deleteScheduleAfterUpdateCreditParamsByCreditId(id);
    }

    private double calculateAnnuityPayment(Credit credit) {
        double amount = credit.getAmount();
        double period = credit.getPeriodCredit();
        double everyMonthPercentRatio = credit.getCostPercentage()/100/12;
        return amount * (everyMonthPercentRatio + everyMonthPercentRatio/(Math.pow((1+everyMonthPercentRatio), period) - 1));
    }
}
