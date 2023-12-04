package org.lesson3;

import java.util.Scanner;

public class CurrencyConverter {
    private double commission;
    private double exchangeRate;

    public CurrencyConverter(double commission, double exchangeRate) {
        this.commission = commission;
        this.exchangeRate = exchangeRate;
    }

    private double convertUsdToUah(double amount) {
        return amount * exchangeRate;
    }


    private double calculateCommission(double uahAmount) {
        return uahAmount * commission / 100;
    }

    public double calculateTotalAmount(double usdAmount) {
        double uahAmount = convertUsdToUah(usdAmount);
        double commissionAmount = calculateCommission(uahAmount);
        return uahAmount - commissionAmount;
    }
}
