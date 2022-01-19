package com.antonbelykh.spring.spring_mvc.rest.dao;

import com.antonbelykh.spring.spring_mvc.rest.dto.Credit;
import com.antonbelykh.spring.spring_mvc.rest.dto.Payment;

import java.util.List;

/**
Набор CRUD операций
 @author Just1kz
 **/
public interface CreditDAOJdbc {

    public List<Credit> getAllCredits();

    public Credit getCreditById(int id);

    public void saveOrUpdateCredit(Credit credit);

    public void deleteCreditById(int id);

    void deleteScheduleAfterUpdateCreditParamsByCreditId(int id);

    public List<Payment> getPaymentsByCreditId(int id);

    public void addPaymentById(int id, Payment payment);
}
