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
        BigDecimal capitalBD;
        try {
            capitalBD = new BigDecimal(capital);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_101);
        }
        BigDecimal periodicAmountBD;
        try {
            periodicAmountBD = new BigDecimal(periodicAmount);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_102);
        }
        BigDecimal interestRateBD;
        try {
            interestRateBD = new BigDecimal(interestRate);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_103);
        }
        int periodInt;
        try {
            periodInt = Integer.parseInt(period);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_104);
        }
        int repetitionCountInt;
        try {
            repetitionCountInt = Integer.parseInt(repetitionCount);
        } catch (NumberFormatException e) {
            throw new InterestException(InterestErrorCode.ERROR_CODE_105);
        }
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

}
