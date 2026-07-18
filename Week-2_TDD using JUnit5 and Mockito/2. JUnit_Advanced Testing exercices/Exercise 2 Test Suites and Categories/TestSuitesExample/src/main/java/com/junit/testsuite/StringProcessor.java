package com.junit.testsuite;

/**
 * A string processing utility class for demonstrating test suites
 */
public class StringProcessor {
    
    public String reverse(String input) {
        if (input == null) {
            return null;
        }
        return new StringBuilder(input).reverse().toString();
    }
    
    public boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }
        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        return cleaned.equals(reverse(cleaned));
    }
    
    public String toUpperCase(String input) {
        return input == null ? null : input.toUpperCase();
    }
    
    public String toLowerCase(String input) {
        return input == null ? null : input.toLowerCase();
    }
    
    public int countWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        return input.trim().split("\\s+").length;
    }
    
    public String removeSpaces(String input) {
        return input == null ? null : input.replaceAll("\\s+", "");
    }
}
