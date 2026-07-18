package com.junit.exception;

/**
 * ExceptionThrower class for demonstrating exception testing
 * Contains various methods that throw different types of exceptions
 */
public class ExceptionThrower {
    
    /**
     * Basic method that throws a RuntimeException
     */
    public void throwException() {
        throw new RuntimeException("This is a runtime exception");
    }
    
    /**
     * Method that throws IllegalArgumentException for invalid input
     */
    public void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }
    
    /**
     * Method that throws IllegalStateException for invalid state
     */
    public void checkState(boolean isValid) {
        if (!isValid) {
            throw new IllegalStateException("Invalid state detected");
        }
    }
    
    /**
     * Method that throws ArithmeticException for division by zero
     */
    public int divide(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return numerator / denominator;
    }
    
    /**
     * Method that throws NumberFormatException for invalid number format
     */
    public int parseNumber(String numberString) {
        if (numberString == null || numberString.trim().isEmpty()) {
            throw new NumberFormatException("Number string cannot be null or empty");
        }
        try {
            return Integer.parseInt(numberString.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format: " + numberString);
        }
    }
    
    /**
     * Method that throws IndexOutOfBoundsException for invalid array access
     */
    public String getArrayElement(String[] array, int index) {
        if (array == null) {
            throw new NullPointerException("Array cannot be null");
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for array length " + array.length);
        }
        return array[index];
    }
    
    /**
     * Method that throws custom exception
     */
    public void processAge(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative: " + age);
        }
        if (age > 150) {
            throw new InvalidAgeException("Age cannot be greater than 150: " + age);
        }
    }
    
    /**
     * Method that may or may not throw exception based on condition
     */
    public String processData(String data, boolean shouldThrow) {
        if (shouldThrow) {
            throw new UnsupportedOperationException("Operation not supported");
        }
        return data.toUpperCase();
    }
    
    /**
     * Method that throws different exceptions based on input
     */
    public void multipleExceptionScenarios(String scenario) {
        switch (scenario.toLowerCase()) {
            case "null":
                throw new NullPointerException("Null scenario triggered");
            case "illegal":
                throw new IllegalArgumentException("Illegal argument scenario triggered");
            case "state":
                throw new IllegalStateException("Illegal state scenario triggered");
            case "arithmetic":
                throw new ArithmeticException("Arithmetic exception scenario triggered");
            case "custom":
                throw new InvalidAgeException("Custom exception scenario triggered");
            default:
                // Normal execution, no exception
                break;
        }
    }
    
    /**
     * Custom exception class for demonstration
     */
    public static class InvalidAgeException extends RuntimeException {
        public InvalidAgeException(String message) {
            super(message);
        }
    }
}
