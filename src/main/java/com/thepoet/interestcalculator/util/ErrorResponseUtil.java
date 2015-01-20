package com.thepoet.interestcalculator.util;

import com.thepoet.interestcalculator.model.ErrorResponse;
import com.thepoet.interestcalculator.service.ErrorResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorResponseUtil {

    @Autowired
    ErrorResponseService errorResponseService;

    public String getJSONResponse(String errorCode, String errorMessage) {
        ErrorResponse errorResponse = errorResponseService.createErrorResponse(errorCode, errorMessage);
        return errorResponseService.toJSON(errorResponse);
    }
}
