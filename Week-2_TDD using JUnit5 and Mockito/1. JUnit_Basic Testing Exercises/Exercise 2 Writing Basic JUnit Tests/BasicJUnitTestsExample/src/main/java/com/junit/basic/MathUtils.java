package com.junit.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple math utility class for basic JUnit testing
 */
public class MathUtils {
    
    /**
     * Calculates the factorial of a number
     * @param n the number
     * @return factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    /**
     * Checks if a number is prime
     * @param n the number to check
     * @return true if prime, false otherwise
     */
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Finds the greatest common divisor of two numbers
     * @param a first number
     * @param b second number
     * @return GCD of a and b
     */
    public int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /**
     * Generates Fibonacci sequence up to n terms
     * @param n number of terms
     * @return list of Fibonacci numbers
     * @throws IllegalArgumentException if n is negative
     */
    public List<Integer> fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of terms cannot be negative");
        }
        
        List<Integer> sequence = new ArrayList<>();
        if (n >= 1) sequence.add(0);
        if (n >= 2) sequence.add(1);
        
        for (int i = 2; i < n; i++) {
            sequence.add(sequence.get(i - 1) + sequence.get(i - 2));
        }
        
        return sequence;
    }
    
    /**
     * Calculates the power of a number
     * @param base the base number
     * @param exponent the exponent
     * @return base raised to the power of exponent
     */
    public double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
