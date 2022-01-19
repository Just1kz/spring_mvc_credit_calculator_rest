package com.antonbelykh.spring.spring_mvc.rest.service;


import com.antonbelykh.spring.spring_mvc.rest.dto.Credit;
import com.antonbelykh.spring.spring_mvc.rest.dto.Payment;

import java.util.List;

public interface CreditService {

    public List<Credit> getAllCredits();

    public Credit getCreditById(int id);

    public void saveOrUpdateCredit(Credit credit);

    public void deleteCreditById(int id);

    public void calculateSchedulePaymentsByCreditId(int id);

    public void deleteScheduleAfterUpdateCreditParamsByCreditId(int id);

    public List<Payment> getPaymentsByCreditId(int id);

    public void deletePaymentsByCreditId(int id);
}
