package com.example.electricitybilling.model;

import java.io.Serializable;

public class BillHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String date;
    private String meterNumber;
    private double unitsConsumed;
    private double amount;

    public BillHistory(String date, String meterNumber, double unitsConsumed, double amount) {
        this.date = date;
        this.meterNumber = meterNumber;
        this.unitsConsumed = unitsConsumed;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BillHistory{" +
                "date='" + date + '\'' +
                ", meterNumber='" + meterNumber + '\'' +
                ", unitsConsumed=" + unitsConsumed +
                ", amount=" + amount +
                '}';
    }
}
