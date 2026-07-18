package com.junit.basic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;

/**
 * JUnit test class for MathUtils
 * Demonstrates testing mathematical operations and exception handling
 */
public class MathUtilsTest {
    
    private MathUtils mathUtils;
    
    @Before
    public void setUp() {
        mathUtils = new MathUtils();
        System.out.println("Setting up MathUtils test...");
    }
    
    @After
    public void tearDown() {
        mathUtils = null;
        System.out.println("MathUtils test completed.\n");
    }
    
    // Tests for factorial method
    @Test
    public void testFactorial() {
        System.out.println("Testing factorial method");
        assertEquals("0! should be 1", 1, mathUtils.factorial(0));
        assertEquals("1! should be 1", 1, mathUtils.factorial(1));
        assertEquals("2! should be 2", 2, mathUtils.factorial(2));
        assertEquals("3! should be 6", 6, mathUtils.factorial(3));
        assertEquals("4! should be 24", 24, mathUtils.factorial(4));
        assertEquals("5! should be 120", 120, mathUtils.factorial(5));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFactorialWithNegativeNumber() {
        System.out.println("Testing factorial with negative number");
        mathUtils.factorial(-1);
    }
    
    // Tests for isPrime method
    @Test
    public void testIsPrime() {
        System.out.println("Testing isPrime method");
        assertFalse("1 should not be prime", mathUtils.isPrime(1));
        assertTrue("2 should be prime", mathUtils.isPrime(2));
        assertTrue("3 should be prime", mathUtils.isPrime(3));
        assertFalse("4 should not be prime", mathUtils.isPrime(4));
        assertTrue("5 should be prime", mathUtils.isPrime(5));
        assertFalse("6 should not be prime", mathUtils.isPrime(6));
        assertTrue("7 should be prime", mathUtils.isPrime(7));
        assertFalse("8 should not be prime", mathUtils.isPrime(8));
        assertFalse("9 should not be prime", mathUtils.isPrime(9));
        assertFalse("10 should not be prime", mathUtils.isPrime(10));
        assertTrue("11 should be prime", mathUtils.isPrime(11));
        assertTrue("13 should be prime", mathUtils.isPrime(13));
        assertTrue("17 should be prime", mathUtils.isPrime(17));
        assertTrue("19 should be prime", mathUtils.isPrime(19));
        assertFalse("20 should not be prime", mathUtils.isPrime(20));
    }
    
    @Test
    public void testIsPrimeWithNegativeAndZero() {
        System.out.println("Testing isPrime with negative and zero");
        assertFalse("0 should not be prime", mathUtils.isPrime(0));
        assertFalse("-1 should not be prime", mathUtils.isPrime(-1));
        assertFalse("-5 should not be prime", mathUtils.isPrime(-5));
    }
    
    // Tests for gcd method
    @Test
    public void testGcd() {
        System.out.println("Testing gcd method");
        assertEquals("gcd(12, 8) should be 4", 4, mathUtils.gcd(12, 8));
        assertEquals("gcd(15, 25) should be 5", 5, mathUtils.gcd(15, 25));
        assertEquals("gcd(17, 19) should be 1", 1, mathUtils.gcd(17, 19));
        assertEquals("gcd(100, 75) should be 25", 25, mathUtils.gcd(100, 75));
        assertEquals("gcd(0, 5) should be 5", 5, mathUtils.gcd(0, 5));
        assertEquals("gcd(5, 0) should be 5", 5, mathUtils.gcd(5, 0));
    }
    
    @Test
    public void testGcdWithNegativeNumbers() {
        System.out.println("Testing gcd with negative numbers");
        assertEquals("gcd(-12, 8) should be 4", 4, mathUtils.gcd(-12, 8));
        assertEquals("gcd(12, -8) should be 4", 4, mathUtils.gcd(12, -8));
        assertEquals("gcd(-12, -8) should be 4", 4, mathUtils.gcd(-12, -8));
    }
    
    // Tests for fibonacci method
    @Test
    public void testFibonacci() {
        System.out.println("Testing fibonacci method");
        
        List<Integer> result0 = mathUtils.fibonacci(0);
        assertTrue("fibonacci(0) should be empty", result0.isEmpty());
        
        List<Integer> result1 = mathUtils.fibonacci(1);
        assertEquals("fibonacci(1) should have 1 element", 1, result1.size());
        assertEquals("fibonacci(1) should be [0]", Arrays.asList(0), result1);
        
        List<Integer> result2 = mathUtils.fibonacci(2);
        assertEquals("fibonacci(2) should be [0, 1]", Arrays.asList(0, 1), result2);
        
        List<Integer> result5 = mathUtils.fibonacci(5);
        assertEquals("fibonacci(5) should be [0, 1, 1, 2, 3]", 
                     Arrays.asList(0, 1, 1, 2, 3), result5);
        
        List<Integer> result8 = mathUtils.fibonacci(8);
        assertEquals("fibonacci(8) should be [0, 1, 1, 2, 3, 5, 8, 13]", 
                     Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13), result8);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFibonacciWithNegativeNumber() {
        System.out.println("Testing fibonacci with negative number");
        mathUtils.fibonacci(-1);
    }
    
    // Tests for power method
    @Test
    public void testPower() {
        System.out.println("Testing power method");
        assertEquals("2^0 should be 1", 1.0, mathUtils.power(2, 0), 0.001);
        assertEquals("2^3 should be 8", 8.0, mathUtils.power(2, 3), 0.001);
        assertEquals("3^2 should be 9", 9.0, mathUtils.power(3, 2), 0.001);
        assertEquals("5^1 should be 5", 5.0, mathUtils.power(5, 1), 0.001);
        assertEquals("10^2 should be 100", 100.0, mathUtils.power(10, 2), 0.001);
    }
    
    @Test
    public void testPowerWithNegativeExponent() {
        System.out.println("Testing power with negative exponent");
        assertEquals("2^(-2) should be 0.25", 0.25, mathUtils.power(2, -2), 0.001);
        assertEquals("4^(-1) should be 0.25", 0.25, mathUtils.power(4, -1), 0.001);
        assertEquals("5^(-1) should be 0.2", 0.2, mathUtils.power(5, -1), 0.001);
    }
    
    @Test
    public void testPowerWithDecimalBase() {
        System.out.println("Testing power with decimal base");
        assertEquals("2.5^2 should be 6.25", 6.25, mathUtils.power(2.5, 2), 0.001);
        assertEquals("1.5^3 should be 3.375", 3.375, mathUtils.power(1.5, 3), 0.001);
    }
    
    // Integration tests - testing multiple methods together
    @Test
    public void testIntegrationScenarios() {
        System.out.println("Testing integration scenarios");
        
        // Test: Find all prime numbers in fibonacci sequence
        List<Integer> fib10 = mathUtils.fibonacci(10);
        int primeCount = 0;
        for (Integer num : fib10) {
            if (mathUtils.isPrime(num)) {
                primeCount++;
            }
        }
        assertEquals("Should find 4 primes in first 10 fibonacci numbers", 4, primeCount);
        
        // Test: Calculate factorial of small prime numbers
        assertTrue("2 is prime", mathUtils.isPrime(2));
        assertEquals("2! should be 2", 2, mathUtils.factorial(2));
        
        assertTrue("3 is prime", mathUtils.isPrime(3));
        assertEquals("3! should be 6", 6, mathUtils.factorial(3));
        
        assertTrue("5 is prime", mathUtils.isPrime(5));
        assertEquals("5! should be 120", 120, mathUtils.factorial(5));
    }
}
