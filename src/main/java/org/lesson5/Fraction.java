package org.lesson5;

public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public void simplify() {
        int greatestCommonDenominator = findGreatestCommonDenominator(Math.abs(numerator), Math.abs(denominator));
        numerator /= greatestCommonDenominator;
        denominator /= greatestCommonDenominator;
    }

    public int findGreatestCommonDenominator(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction other = (Fraction) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }

}