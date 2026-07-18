package com.junit.parameterized;

/**
 * A utility class to check if numbers are even
 * Used to demonstrate parameterized testing in JUnit 5
 */
public class EvenChecker {
    
    /**
     * Checks if a given number is even
     * @param number the number to check
     * @return true if the number is even, false if odd
     */
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    /**
     * Checks if a number is odd (opposite of even)
     * @param number the number to check
     * @return true if the number is odd, false if even
     */
    public boolean isOdd(int number) {
        return !isEven(number);
    }
    
    /**
     * Checks if a number is positive and even
     * @param number the number to check
     * @return true if the number is both positive and even
     */
    public boolean isPositiveEven(int number) {
        return number > 0 && isEven(number);
    }
    
    /**
     * Gets the next even number greater than the given number
     * @param number the starting number
     * @return the next even number
     */
    public int getNextEven(int number) {
        if (isEven(number)) {
            return number + 2;
        } else {
            return number + 1;
        }
    }
    
    /**
     * Counts how many even numbers are in a range (inclusive)
     * @param start the start of the range
     * @param end the end of the range
     * @return count of even numbers in the range
     */
    public int countEvensInRange(int start, int end) {
        if (start > end) {
            return 0;
        }
        
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (isEven(i)) {
                count++;
            }
        }
        return count;
    }
}
