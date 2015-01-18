package com.thepoet.interestcalculator.controller;

import com.thepoet.interestcalculator.model.Interest;
import com.thepoet.interestcalculator.service.InterestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class CalculatorController {
    @Autowired
    InterestService interestService;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ModelAndView apiGet(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("interest_api_json");
        String startAmount = this.getParameter(request.getParameter("startAmount"), "1000");
        String periodicAmount = this.getParameter(request.getParameter("periodicAmount"), "0");
        String interestRate = this.getParameter(request.getParameter("interestRate"), "10.50");
        String period = this.getParameter(request.getParameter("period"), "32");
        String repetitionCount = this.getParameter(request.getParameter("repetitionCount"), "1");
        String jsonResult = this.getJSON(startAmount, periodicAmount, interestRate, period, repetitionCount);
        //TODO don't use modelandview here
        mav.addObject("jsonResult", jsonResult);
        return mav;
    }

    private List<Interest> getList(String startAmount, String periodicAmount, String interestRate, String period, String repetitionCount) {
        BigDecimal startAmountBD = new BigDecimal(startAmount);
        BigDecimal periodicAmountBD = new BigDecimal(periodicAmount);
        BigDecimal interestRateBD = new BigDecimal(interestRate);
        int periodInt = Integer.parseInt(period);
        int repetitionCountInt = Integer.parseInt(repetitionCount);
        return interestService.calculate(startAmountBD, periodicAmountBD, interestRateBD, periodInt, repetitionCountInt);
    }

    private String getJSON(String startAmount, String periodicAmount, String interestRate, String period, String repetitionCount) {
        List<Interest> interestList = this.getList(startAmount, periodicAmount, interestRate, period, repetitionCount);
        return interestService.toJson(interestList);
    }

    private String getParameter(String parameter, String defaultValue) {
        if (StringUtils.isNotBlank(parameter)) {
            return parameter;
        }
        return defaultValue;
    }
}