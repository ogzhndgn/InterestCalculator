package com.thepoet.interestcalculator.util;

import com.thepoet.interestcalculator.enums.InterestErrorCode;
import com.thepoet.interestcalculator.exception.InterestException;
import com.thepoet.interestcalculator.model.Interest;
import com.thepoet.interestcalculator.service.InterestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InterestUtil {
    @Autowired
    InterestService interestService;

    private List<Interest> getList(String capital, String periodicAmount, String interestRate, String period, String repetitionCount) throws InterestException {
        BigDecimal capitalBD = getCapital(capital);
        BigDecimal periodicAmountBD = getPeriodicAmount(periodicAmount);
        BigDecimal interestRateBD = getInterestRate(interestRate);
        int periodInt = getPeriod(period);
        int repetitionCountInt = getRepetitionCount(repetitionCount);
        return interestService.calculate(capitalBD, periodicAmountBD, interestRateBD, periodInt, repetitionCountInt);
    }

    public String getJSONResponse(String capital, String periodicAmount, String interestRate, String period, String repetitionCount) throws InterestException {
        List<Interest> interestList = this.getList(capital, periodicAmount, interestRate, period, repetitionCount);
        return interestService.toJson(interestList);
    }

    public String getParameter(String parameter, String defaultValue) {
        if (StringUtils.isNotBlank(parameter)) {
            return parameter;
        }
        return defaultValue;
    }

    private int getRepetitionCount(String repetitionCount) throws InterestException {
        int repetitionCountInt;
        try {
            repetitionCountInt = Integer.parseInt(repetitionCount);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_105);
        }
        return repetitionCountInt;
    }

    private int getPeriod(String period) throws InterestException {
        int periodInt;
        try {
            periodInt = Integer.parseInt(period);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_104);
        }
        return periodInt;
    }

    private BigDecimal getInterestRate(String interestRate) throws InterestException {
        BigDecimal interestRateBD;
        try {
            interestRateBD = new BigDecimal(interestRate);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_103);
        }
        return interestRateBD;
    }

    private BigDecimal getPeriodicAmount(String periodicAmount) throws InterestException {
        BigDecimal periodicAmountBD;
        try {
            periodicAmountBD = new BigDecimal(periodicAmount);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_102);
        }
        return periodicAmountBD;
    }

    private BigDecimal getCapital(String capital) throws InterestException {
        BigDecimal capitalBD;
        try {
            capitalBD = new BigDecimal(capital);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_101);
        }
        return capitalBD;
    }
}