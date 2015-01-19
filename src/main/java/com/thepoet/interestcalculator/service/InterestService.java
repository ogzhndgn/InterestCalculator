package com.thepoet.interestcalculator.service;

import com.google.gson.Gson;
import com.thepoet.interestcalculator.model.Interest;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class InterestService {
    private static final int DAYS_IN_A_YEAR = 365;
    private static final double TAX_AMOUNT = 0.85;

    public List<Interest> calculate(BigDecimal capital, BigDecimal periodicAmount, BigDecimal interestRate, int periodInDays, int repetitionCount) {
        List<Interest> interestList = new ArrayList<>();
        for (int period = 1; period <= repetitionCount; period++) {
            BigDecimal interestInAYear = this.calculateInterestInAYear(capital, interestRate);
            BigDecimal grossInterestInPeriod = this.calculateGrossInterestInPeriod(interestInAYear, periodInDays);
            BigDecimal netInterestInPeriod = this.calculateNetInterestInPeriod(grossInterestInPeriod);
            BigDecimal netTotalInPeriod = capital.add(netInterestInPeriod);
            BigDecimal grossTotalInPeriod = capital.add(grossInterestInPeriod);
            interestList.add(createInterest(period, capital, grossInterestInPeriod, netInterestInPeriod, netTotalInPeriod, grossTotalInPeriod, periodicAmount));
            capital = capital.add(netInterestInPeriod).add(periodicAmount);
        }
        return interestList;
    }

    public String toJson(List<Interest> interestList) {
        GsonFactoryBean gsonFactoryBean = new GsonFactoryBean();
        gsonFactoryBean.setPrettyPrinting(true);
        gsonFactoryBean.afterPropertiesSet();
        Gson gson = gsonFactoryBean.getObject();
        return gson.toJson(interestList);
    }

    private Interest createInterest(int period, BigDecimal capital, BigDecimal grossInterestInPeriod, BigDecimal netInterestInPeriod, BigDecimal netTotalInPeriod, BigDecimal grossTotalInPeriod, BigDecimal periodicAmount) {
        Interest interest = new Interest();
        interest.setPeriod(period);
        interest.setCapital(capital);
        interest.setNetInterestInPeriod(netInterestInPeriod);
        interest.setGrossInterestInPeriod(grossInterestInPeriod);
        interest.setNetTotalInPeriod(netTotalInPeriod);
        interest.setGrossTotalInPeriod(grossTotalInPeriod);
        interest.setPeriodicAmount(periodicAmount);
        return interest;
    }

    private BigDecimal calculateInterestInAYear(BigDecimal amount, BigDecimal interestRate) {
        return amount.multiply(interestRate).divide(BigDecimal.valueOf(100));
    }

    private BigDecimal calculateGrossInterestInPeriod(BigDecimal interestInAYear, int periodInDays) {
        BigDecimal grossInterestInPeriod = interestInAYear.multiply(BigDecimal.valueOf(periodInDays)).divide(BigDecimal.valueOf(DAYS_IN_A_YEAR), RoundingMode.HALF_UP);
        return grossInterestInPeriod.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateNetInterestInPeriod(BigDecimal grossInterestInPeriod) {
        BigDecimal netInterestInPeriod = grossInterestInPeriod.multiply(BigDecimal.valueOf(TAX_AMOUNT));
        return netInterestInPeriod.setScale(2, RoundingMode.HALF_UP);
    }
}