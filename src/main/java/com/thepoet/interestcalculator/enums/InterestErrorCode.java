package com.thepoet.interestcalculator.enums;

public enum InterestErrorCode {
    /**
     * Capital must be numeric
     */
    ERROR_CODE_101("Capital must be numeric"),
    /**
     * Periodic amount must be numberic
     */
    ERROR_CODE_102("Periodic amount must be numberic"),
    /**
     * Interest rate must be numeric
     */
    ERROR_CODE_103("Interest rate must be numeric"),
    /**
     * Period must be numeric
     */
    ERROR_CODE_104("Period must be numeric"),
    /**
     * Repetition count must be numeric
     */
    ERROR_CODE_105("Repetition count must be numeric"),
    /**
     * Capital must be between 1 and 500000
     */
    ERROR_CODE_201("Capital must be between 1 and 500000"),
    /**
     * Period must be between 0 and 365
     */
    ERROR_CODE_204("Period must be between 0 and 365"),
    /**
     * Repetition count must be between 1 and 300
     */
    ERROR_CODE_205("Repetition count must be between 1 and 300");

    private String errorMessage;

    private InterestErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
