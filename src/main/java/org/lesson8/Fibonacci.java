package org.lesson8;

import java.util.stream.Stream;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 10; // Замініть на бажане число Фібоначчі
        long result = calculateFibonacci(n);
        System.out.println("Число Фібоначчі для N=" + n + ": " + result);
    }

    private static long calculateFibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N має бути не менше 0");
        }

        return Stream.iterate(new long[]{0, 1}, fib -> new long[]{fib[1], fib[0] + fib[1]})
                .limit(n + 1)
                .reduce((first, second) -> second)
                .orElseThrow()[0];
    }
}
