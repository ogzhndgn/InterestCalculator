package com.thepoet.interestcalculator.model;

import java.math.BigDecimal;

public class Interest {
    private int period;
    private BigDecimal capital;
    private BigDecimal grossInterestInPeriod;
    private BigDecimal netInterestInPeriod;
    private BigDecimal grossTotalInPeriod;
    private BigDecimal netTotalInPeriod;
    private BigDecimal periodicAmount;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getGrossInterestInPeriod() {
        return grossInterestInPeriod;
    }

    public void setGrossInterestInPeriod(BigDecimal grossInterestInPeriod) {
        this.grossInterestInPeriod = grossInterestInPeriod;
    }

    public BigDecimal getNetInterestInPeriod() {
        return netInterestInPeriod;
    }

    public void setNetInterestInPeriod(BigDecimal netInterestInPeriod) {
        this.netInterestInPeriod = netInterestInPeriod;
    }

    public BigDecimal getGrossTotalInPeriod() {
        return grossTotalInPeriod;
    }

    public void setGrossTotalInPeriod(BigDecimal grossTotalInPeriod) {
        this.grossTotalInPeriod = grossTotalInPeriod;
    }

    public BigDecimal getNetTotalInPeriod() {
        return netTotalInPeriod;
    }

    public void setNetTotalInPeriod(BigDecimal netTotalInPeriod) {
        this.netTotalInPeriod = netTotalInPeriod;
    }

    public BigDecimal getPeriodicAmount() {
        return periodicAmount;
    }

    public void setPeriodicAmount(BigDecimal periodicAmount) {
        this.periodicAmount = periodicAmount;
    }
}