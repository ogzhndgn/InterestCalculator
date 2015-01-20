package com.thepoet.interestcalculator.controller;

import com.thepoet.interestcalculator.exception.InterestException;
import com.thepoet.interestcalculator.util.ErrorResponseUtil;
import com.thepoet.interestcalculator.util.InterestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CalculatorController {
    @Autowired
    InterestUtil interestUtil;
    @Autowired
    ErrorResponseUtil errorResponseUtil;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ModelAndView apiGet(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("interest_api_json");
        String jsonResult;
        try {
            String capital = interestUtil.getParameter(request.getParameter("capital"), "1000");
            String periodicAmount = interestUtil.getParameter(request.getParameter("periodicAmount"), "0");
            String interestRate = interestUtil.getParameter(request.getParameter("interestRate"), "10.50");
            String period = interestUtil.getParameter(request.getParameter("period"), "32");
            String repetitionCount = interestUtil.getParameter(request.getParameter("repetitionCount"), "1");
            jsonResult = interestUtil.getJSONResponse(capital, periodicAmount, interestRate, period, repetitionCount);
        } catch (InterestException interestException) {
            jsonResult = errorResponseUtil.getJSONResponse(interestException.getInterestErrorCode().name(), interestException.getInterestErrorCode().getErrorMessage());
        }
        mav.addObject("jsonResult", jsonResult);
        return mav;
    }
}