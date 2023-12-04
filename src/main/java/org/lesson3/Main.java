package org.lesson3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input USD amount:");

        double usdAmount = sc.nextDouble();

        CurrencyConverter currencyConverter = new CurrencyConverter(1.0, 36.55);

        double uahAmount = currencyConverter.calculateTotalAmount(usdAmount);

        System.out.println("Result: "+ uahAmount);
    }

}
