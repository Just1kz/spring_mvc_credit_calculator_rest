package com.antonbelykh.spring.spring_mvc.rest.dao;

import com.antonbelykh.spring.spring_mvc.rest.dto.Credit;
import com.antonbelykh.spring.spring_mvc.rest.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CreditDAOJdbcImpl implements CreditDAOJdbc {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CreditDAOJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Credit> creditRowMapper = ((rs, row) -> new Credit(
            rs.getInt("id"),
            rs.getInt("periodCredit"),
            rs.getDouble("amount"),
            rs.getDouble("costPercentage"),
            rs.getString("status")));

    private final RowMapper<Payment> paymentRowMapper = ((rs, row) -> new Payment(
            rs.getInt("id"),
            rs.getInt("month"),
            rs.getDouble("debtBeforePayment"),
            rs.getDouble("percentPayment"),
            rs.getDouble("debtPayment"),
            rs.getDouble("payment"),
            rs.getDouble("debtAfterPayment")));

    @Override
    public List<Credit> getAllCredits() {
        List<Credit> rsl = new ArrayList<>();
        List<Credit> transportList = jdbcTemplate.query("select id, periodCredit, amount, costPercentage, status from credit order by id",
                creditRowMapper);
        for (Credit credit: transportList) {
            credit.setPaymentList(getPaymentsByCreditId(credit.getId()));
            rsl.add(credit);
        }
        return rsl;
    }

    @Override
    public Credit getCreditById(int id) {
        Credit credit =  jdbcTemplate.queryForObject("select id, periodCredit, amount, costPercentage, status from credit where id = ?",
                creditRowMapper, id);
        if (credit != null) {
            credit.setPaymentList(getPaymentsByCreditId(id));
        }
        return credit;
    }

    @Override
    public void saveOrUpdateCredit(Credit credit) {
        if (credit.getId() == 0) {
            jdbcTemplate.update("insert into credit(periodCredit, amount, costPercentage) values (?, ?, ?)",
                    credit.getPeriodCredit(),
                    credit.getAmount(),
                    credit.getCostPercentage());
        } else {
            jdbcTemplate.update("update credit set periodCredit = ?, amount = ?, costPercentage = ?, status = ? where id = ?",
                    credit.getPeriodCredit(),
                    credit.getAmount(),
                    credit.getCostPercentage(),
                    "Payments is not calculated",
                    credit.getId());
        }
    }

    @Override
    public void deleteCreditById(int id) {
        jdbcTemplate.update("delete from credit where id = ?", id);
    }

    @Override
    public void deleteScheduleAfterUpdateCreditParamsByCreditId(int id) {
        jdbcTemplate.update("delete from payment where credit_id = ?", id);
    }

    @Override
    public List<Payment> getPaymentsByCreditId(int id) {
        return jdbcTemplate.query("select id, month, debtBeforePayment, percentPayment, debtPayment, payment, debtAfterPayment " +
                        "from payment where credit_id = ? order by id",
                paymentRowMapper, id);
    }

    @Override
    public void addPaymentById(int id, Payment payment) {
        jdbcTemplate.update("insert into payment(month, debtBeforePayment, percentPayment, debtPayment, payment, debtAfterPayment, credit_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",
                payment.getMonth(),
                payment.getDebtBeforePayment(),
                payment.getPercentPayment(),
                payment.getDebtPayment(),
                payment.getPayment(),
                payment.getDebtAfterPayment(),
                id);
    }
}
