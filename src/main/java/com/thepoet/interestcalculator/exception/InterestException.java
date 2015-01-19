package com.thepoet.interestcalculator.exception;

import com.thepoet.interestcalculator.enums.InterestErrorCode;

public class InterestException extends Exception {
    InterestErrorCode interestErrorCode;

    public InterestException(InterestErrorCode interestErrorCode) {
        this.interestErrorCode = interestErrorCode;
    }

    public InterestErrorCode getInterestErrorCode() {
        return this.interestErrorCode;
    }
}
