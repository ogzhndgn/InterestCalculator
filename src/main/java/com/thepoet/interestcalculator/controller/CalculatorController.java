package com.thepoet.interestcalculator.controller;

import com.thepoet.interestcalculator.exception.InterestException;
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

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ModelAndView apiGet(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("interest_api_json");
        try {
            String capital = interestUtil.getParameter(request.getParameter("capital"), "1000");
            String periodicAmount = interestUtil.getParameter(request.getParameter("periodicAmount"), "0");
            String interestRate = interestUtil.getParameter(request.getParameter("interestRate"), "10.50");
            String period = interestUtil.getParameter(request.getParameter("period"), "32");
            String repetitionCount = interestUtil.getParameter(request.getParameter("repetitionCount"), "1");
            String jsonResult = interestUtil.getJSON(capital, periodicAmount, interestRate, period, repetitionCount);
            //TODO don't use modelandview here
            mav.addObject("jsonResult", jsonResult);
        } catch (InterestException interestException) {
            mav.addObject("jsonResult", interestException.getInterestErrorCode());
        }
        return mav;
    }
}