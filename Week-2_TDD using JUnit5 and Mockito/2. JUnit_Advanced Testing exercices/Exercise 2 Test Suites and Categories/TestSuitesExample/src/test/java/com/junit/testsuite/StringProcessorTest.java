package com.junit.testsuite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StringProcessor demonstrating test categorization with tags
 */
@DisplayName("StringProcessor Tests")
public class StringProcessorTest {
    
    private StringProcessor stringProcessor;
    
    @BeforeEach
    void setUp() {
        stringProcessor = new StringProcessor();
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.SMOKE)
    @DisplayName("String reversal should work correctly")
    void testReverse() {
        assertEquals("olleh", stringProcessor.reverse("hello"));
        assertEquals("a", stringProcessor.reverse("a"));
        assertEquals("", stringProcessor.reverse(""));
        assertEquals("54321", stringProcessor.reverse("12345"));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.EDGE_CASE)
    @DisplayName("Reverse with null input should return null")
    void testReverseNull() {
        assertNull(stringProcessor.reverse(null));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Palindrome detection should work correctly")
    void testIsPalindrome() {
        assertTrue(stringProcessor.isPalindrome("racecar"));
        assertTrue(stringProcessor.isPalindrome("A man a plan a canal Panama"));
        assertFalse(stringProcessor.isPalindrome("race a car"));  // This is not a palindrome
        assertTrue(stringProcessor.isPalindrome("a"));
        assertTrue(stringProcessor.isPalindrome(""));
        assertFalse(stringProcessor.isPalindrome("hello"));
        assertFalse(stringProcessor.isPalindrome("world"));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.EDGE_CASE)
    @DisplayName("Palindrome with null input should return false")
    void testIsPalindromeNull() {
        assertFalse(stringProcessor.isPalindrome(null));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Uppercase conversion should work correctly")
    void testToUpperCase() {
        assertEquals("HELLO", stringProcessor.toUpperCase("hello"));
        assertEquals("WORLD", stringProcessor.toUpperCase("WoRlD"));
        assertEquals("123", stringProcessor.toUpperCase("123"));
        assertEquals("", stringProcessor.toUpperCase(""));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.EDGE_CASE)
    @DisplayName("Uppercase with null input should return null")
    void testToUpperCaseNull() {
        assertNull(stringProcessor.toUpperCase(null));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Lowercase conversion should work correctly")
    void testToLowerCase() {
        assertEquals("hello", stringProcessor.toLowerCase("HELLO"));
        assertEquals("world", stringProcessor.toLowerCase("WoRlD"));
        assertEquals("123", stringProcessor.toLowerCase("123"));
        assertEquals("", stringProcessor.toLowerCase(""));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.EDGE_CASE)
    @DisplayName("Lowercase with null input should return null")
    void testToLowerCaseNull() {
        assertNull(stringProcessor.toLowerCase(null));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Word counting should work correctly")
    void testCountWords() {
        assertEquals(2, stringProcessor.countWords("hello world"));
        assertEquals(1, stringProcessor.countWords("hello"));
        assertEquals(4, stringProcessor.countWords("one two three four"));
        assertEquals(3, stringProcessor.countWords("  multiple   spaces   here  "));
        assertEquals(0, stringProcessor.countWords(""));
        assertEquals(0, stringProcessor.countWords("   "));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.EDGE_CASE)
    @DisplayName("Word counting with null input should return 0")
    void testCountWordsNull() {
        assertEquals(0, stringProcessor.countWords(null));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Space removal should work correctly")
    void testRemoveSpaces() {
        assertEquals("helloworld", stringProcessor.removeSpaces("hello world"));
        assertEquals("abc", stringProcessor.removeSpaces("a b c"));
        assertEquals("test", stringProcessor.removeSpaces("  test  "));  
        assertEquals("multiplespaces", stringProcessor.removeSpaces("multiple   spaces"));
        assertEquals("", stringProcessor.removeSpaces(""));
        assertEquals("", stringProcessor.removeSpaces("   "));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.EDGE_CASE)
    @DisplayName("Space removal with null input should return null")
    void testRemoveSpacesNull() {
        assertNull(stringProcessor.removeSpaces(null));
    }
    
    @Test
    @Tag(TestCategories.SLOW)
    @Tag(TestCategories.STRING)
    @Tag(TestCategories.PERFORMANCE)
    @DisplayName("Performance test with large strings")
    void testLargeStringProcessing() {
        // Create a large string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("word").append(i).append(" ");
        }
        String largeString = sb.toString();
        
        // Test that operations complete without timeout
        assertDoesNotThrow(() -> {
            stringProcessor.countWords(largeString);
            stringProcessor.removeSpaces(largeString);
            stringProcessor.toUpperCase(largeString);
        });
    }
    
    @Test
    @Tag(TestCategories.REGRESSION)
    @Tag(TestCategories.STRING)
    @DisplayName("Complex string operations")
    void testComplexOperations() {
        String input = "A Man A Plan A Canal Panama";
        
        // Test chaining operations
        String processed = stringProcessor.removeSpaces(
            stringProcessor.toLowerCase(input)
        );
        
        assertEquals("amanaplanacanalpanama", processed);
        assertTrue(stringProcessor.isPalindrome(input));
    }
    
    @Test
    @Tag(TestCategories.EDGE_CASE)
    @Tag(TestCategories.STRING)
    @DisplayName("Special characters and unicode")
    void testSpecialCharacters() {
        assertEquals(")*&^%$#@!", stringProcessor.reverse("!@#$%^&*()"));
        assertEquals("HÉLLO WÖRLD", stringProcessor.toUpperCase("héllo wörld"));
        assertEquals(3, stringProcessor.countWords("hello! world? test."));
        assertEquals("émojis🚀test", stringProcessor.removeSpaces("émojis 🚀 test"));
    }
}
