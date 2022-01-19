package com.antonbelykh.spring.spring_mvc.rest.controller;

import com.antonbelykh.spring.spring_mvc.rest.dto.Credit;
import com.antonbelykh.spring.spring_mvc.rest.dto.Payment;
import com.antonbelykh.spring.spring_mvc.rest.exception_handling.NoSuchCreditException;
import com.antonbelykh.spring.spring_mvc.rest.exception_handling.NoSuchPaymentException;
import com.antonbelykh.spring.spring_mvc.rest.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MyRestController {

    private final CreditService creditService;

    @Autowired
    public MyRestController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/credits")
    public List<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/credits/{id}")
    public Credit getCreditById(@PathVariable Integer id) {
        Credit credit = creditService.getCreditById(id);
        if(credit == null) {
            throw new NoSuchCreditException("There is no credit with id = " + id + "in DataBase");
        }
        return credit;
    }

    @GetMapping("/credits/showPayments/{id}")
    public List<Payment> showPaymentsOfCredit(@PathVariable Integer id) {
        List<Payment> paymentsByCreditId = creditService.getPaymentsByCreditId(id);
        if (paymentsByCreditId == null) {
            throw new NoSuchPaymentException("Schedule payments of credit with id = " + id +
                    "is not Calculated! Pls Calculate before get.");
        }
        return paymentsByCreditId;
    }

    @PostMapping("/credits")
    public String addCredit(@RequestBody Credit credit) {
        creditService.saveOrUpdateCredit(credit);
        return "Your credit is added!";
    }

    @PutMapping("/credits")
    public Credit editCredit(@RequestBody Credit credit) {
        creditService.deleteScheduleAfterUpdateCreditParamsByCreditId(credit.getId());
        credit.setStatus("Payments is not calculated");
        creditService.saveOrUpdateCredit(credit);
        return credit;
    }

    @DeleteMapping("/credits/{id}")
    public String deleteCredit(@PathVariable Integer id) {
        creditService.deleteCreditById(id);
        return "Delete credit = " + id + " is Done";
    }

    @GetMapping("credits/calculate/{id}")
    public String calculateSchedulePayments(@PathVariable Integer id) {
        creditService.calculateSchedulePaymentsByCreditId(id);
        return "Calculate credit = " + id + " is Done";
    }
}
