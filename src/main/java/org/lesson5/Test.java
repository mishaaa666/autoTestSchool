package org.lesson5;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input first fraction:");
        int numerator1 = scanner.nextInt();
        int denominator1 = scanner.nextInt();
        Fraction fraction1 = new Fraction(numerator1, denominator1);

        System.out.println("Input second fraction:");
        int numerator2 = scanner.nextInt();
        int denominator2 = scanner.nextInt();
        Fraction fraction2 = new Fraction(numerator2, denominator2);

        System.out.println("Do the fractions equal? - " + fraction1.equals(fraction2));
    }


}
