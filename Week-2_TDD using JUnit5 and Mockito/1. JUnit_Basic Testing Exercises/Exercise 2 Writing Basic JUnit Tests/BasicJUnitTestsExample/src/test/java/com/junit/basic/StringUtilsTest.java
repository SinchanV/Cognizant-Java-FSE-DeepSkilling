package com.junit.basic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * JUnit test class for StringUtils
 * Demonstrates various testing scenarios and assertions
 */
public class StringUtilsTest {
    
    private StringUtils stringUtils;
    
    @Before
    public void setUp() {
        stringUtils = new StringUtils();
        System.out.println("Setting up StringUtils test...");
    }
    
    @After
    public void tearDown() {
        stringUtils = null;
        System.out.println("StringUtils test completed.\n");
    }
    
    // Tests for reverse method
    @Test
    public void testReverse() {
        System.out.println("Testing reverse method");
        assertEquals("olleh", stringUtils.reverse("hello"));
        assertEquals("dlrow", stringUtils.reverse("world"));
        assertEquals("", stringUtils.reverse(""));
        assertEquals("a", stringUtils.reverse("a"));
    }
    
    @Test
    public void testReverseWithNull() {
        System.out.println("Testing reverse with null");
        assertNull("Reverse of null should be null", stringUtils.reverse(null));
    }
    
    // Tests for isPalindrome method
    @Test
    public void testIsPalindrome() {
        System.out.println("Testing isPalindrome method");
        assertTrue("'racecar' should be palindrome", stringUtils.isPalindrome("racecar"));
        assertTrue("'A man a plan a canal Panama' should be palindrome", 
                   stringUtils.isPalindrome("A man a plan a canal Panama"));
        assertTrue("'Was it a car or a cat I saw' should be palindrome", 
                   stringUtils.isPalindrome("Was it a car or a cat I saw"));
        assertFalse("'hello' should not be palindrome", stringUtils.isPalindrome("hello"));
        assertFalse("'world' should not be palindrome", stringUtils.isPalindrome("world"));
    }
    
    @Test
    public void testIsPalindromeWithNull() {
        System.out.println("Testing isPalindrome with null");
        assertFalse("null should not be palindrome", stringUtils.isPalindrome(null));
    }
    
    // Tests for countVowels method
    @Test
    public void testCountVowels() {
        System.out.println("Testing countVowels method");
        assertEquals("'hello' should have 2 vowels", 2, stringUtils.countVowels("hello"));
        assertEquals("'HELLO' should have 2 vowels", 2, stringUtils.countVowels("HELLO"));
        assertEquals("'aeiou' should have 5 vowels", 5, stringUtils.countVowels("aeiou"));
        assertEquals("'bcdfg' should have 0 vowels", 0, stringUtils.countVowels("bcdfg"));
        assertEquals("'Programming' should have 3 vowels", 3, stringUtils.countVowels("Programming"));
    }
    
    @Test
    public void testCountVowelsWithNull() {
        System.out.println("Testing countVowels with null");
        assertEquals("null should have 0 vowels", 0, stringUtils.countVowels(null));
    }
    
    // Tests for capitalizeWords method
    @Test
    public void testCapitalizeWords() {
        System.out.println("Testing capitalizeWords method");
        assertEquals("Hello World", stringUtils.capitalizeWords("hello world"));
        assertEquals("Java Programming", stringUtils.capitalizeWords("JAVA PROGRAMMING"));
        assertEquals("Test Case", stringUtils.capitalizeWords("test case"));
        assertEquals("A", stringUtils.capitalizeWords("a"));
        assertEquals("", stringUtils.capitalizeWords(""));
    }
    
    @Test
    public void testCapitalizeWordsWithNull() {
        System.out.println("Testing capitalizeWords with null");
        assertNull("null should return null", stringUtils.capitalizeWords(null));
    }
    
    @Test
    public void testCapitalizeWordsWithExtraSpaces() {
        System.out.println("Testing capitalizeWords with extra spaces");
        assertEquals("Hello World", stringUtils.capitalizeWords("  hello   world  "));
    }
    
    // Tests for removeExtraSpaces method
    @Test
    public void testRemoveExtraSpaces() {
        System.out.println("Testing removeExtraSpaces method");
        assertEquals("hello world", stringUtils.removeExtraSpaces("hello    world"));
        assertEquals("test case", stringUtils.removeExtraSpaces("  test   case  "));
        assertEquals("single", stringUtils.removeExtraSpaces("single"));
        assertEquals("", stringUtils.removeExtraSpaces("   "));
        assertEquals("a b c", stringUtils.removeExtraSpaces("a  b  c"));
    }
    
    @Test
    public void testRemoveExtraSpacesWithNull() {
        System.out.println("Testing removeExtraSpaces with null");
        assertNull("null should return null", stringUtils.removeExtraSpaces(null));
    }
    
    // Tests for isNumeric method
    @Test
    public void testIsNumeric() {
        System.out.println("Testing isNumeric method");
        assertTrue("'12345' should be numeric", stringUtils.isNumeric("12345"));
        assertTrue("'0' should be numeric", stringUtils.isNumeric("0"));
        assertTrue("'999' should be numeric", stringUtils.isNumeric("999"));
        assertFalse("'12a45' should not be numeric", stringUtils.isNumeric("12a45"));
        assertFalse("'hello' should not be numeric", stringUtils.isNumeric("hello"));
        assertFalse("'12.34' should not be numeric", stringUtils.isNumeric("12.34"));
        assertFalse("'-123' should not be numeric", stringUtils.isNumeric("-123"));
    }
    
    @Test
    public void testIsNumericWithNullAndEmpty() {
        System.out.println("Testing isNumeric with null and empty");
        assertFalse("null should not be numeric", stringUtils.isNumeric(null));
        assertFalse("empty string should not be numeric", stringUtils.isNumeric(""));
    }
    
    // Edge case tests
    @Test
    public void testEdgeCases() {
        System.out.println("Testing edge cases");
        
        // Single character tests
        assertEquals("a", stringUtils.reverse("a"));
        assertTrue("'a' should be palindrome", stringUtils.isPalindrome("a"));
        assertEquals(1, stringUtils.countVowels("a"));
        assertEquals("A", stringUtils.capitalizeWords("a"));
        assertTrue("'5' should be numeric", stringUtils.isNumeric("5"));
        
        // Empty string tests
        assertEquals("", stringUtils.reverse(""));
        assertEquals(0, stringUtils.countVowels(""));
        assertEquals("", stringUtils.capitalizeWords(""));
        assertEquals("", stringUtils.removeExtraSpaces(""));
        assertFalse("empty string should not be numeric", stringUtils.isNumeric(""));
    }
}
