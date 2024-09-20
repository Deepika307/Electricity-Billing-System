package com.example.electricitybilling.model;

public class ElectricityBill {

    public static double calculateBillAmount(double unitsConsumed, double ratePerUnit) {
        return unitsConsumed * ratePerUnit;
    }
}
