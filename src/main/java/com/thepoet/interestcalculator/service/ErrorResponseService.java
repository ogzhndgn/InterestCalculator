package com.thepoet.interestcalculator.service;

import com.google.gson.Gson;
import com.thepoet.interestcalculator.model.ErrorResponse;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class ErrorResponseService {

    public ErrorResponse createErrorResponse(String errorCode, String errorMessage) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setErrorMessage(errorMessage);
        return errorResponse;
    }

    public String toJSON(ErrorResponse errorResponse) {
        GsonFactoryBean gsonFactoryBean = new GsonFactoryBean();
        gsonFactoryBean.setPrettyPrinting(true);
        gsonFactoryBean.afterPropertiesSet();
        Gson gson = gsonFactoryBean.getObject();
        return gson.toJson(errorResponse);
    }
}
