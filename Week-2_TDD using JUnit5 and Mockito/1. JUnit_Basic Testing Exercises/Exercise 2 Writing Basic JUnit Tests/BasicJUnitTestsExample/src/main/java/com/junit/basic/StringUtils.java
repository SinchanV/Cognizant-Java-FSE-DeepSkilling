package com.junit.basic;

/**
 * A utility class for string operations to demonstrate basic JUnit testing
 */
public class StringUtils {
    
    /**
     * Reverses a string
     * @param str the string to reverse
     * @return reversed string
     */
    public String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }
    
    /**
     * Checks if a string is a palindrome
     * @param str the string to check
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        String cleaned = str.toLowerCase().replaceAll("[^a-z0-9]", "");
        return cleaned.equals(reverse(cleaned));
    }
    
    /**
     * Counts the number of vowels in a string
     * @param str the string to count vowels in
     * @return number of vowels
     */
    public int countVowels(String str) {
        if (str == null) {
            return 0;
        }
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : str.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Capitalizes the first letter of each word
     * @param str the string to capitalize
     * @return capitalized string
     */
    public String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        // Trim the string first to remove leading/trailing spaces
        String trimmed = str.trim();
        if (trimmed.isEmpty()) {
            return "";
        }
        
        String[] words = trimmed.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                result.append(Character.toUpperCase(words[i].charAt(0)));
                if (words[i].length() > 1) {
                    result.append(words[i].substring(1).toLowerCase());
                }
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Removes extra spaces from a string
     * @param str the string to clean
     * @return string with single spaces
     */
    public String removeExtraSpaces(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("\\s+", " ");
    }
    
    /**
     * Checks if a string contains only digits
     * @param str the string to check
     * @return true if contains only digits, false otherwise
     */
    public boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
