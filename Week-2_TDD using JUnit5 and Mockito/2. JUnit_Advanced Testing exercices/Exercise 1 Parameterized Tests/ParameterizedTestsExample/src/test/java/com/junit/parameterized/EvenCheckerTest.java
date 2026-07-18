package com.junit.parameterized;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;

/**
 * Comprehensive parameterized test class for EvenChecker
 * Demonstrates various JUnit 5 parameterized testing features
 */
@DisplayName("EvenChecker Parameterized Tests")
public class EvenCheckerTest {
    
    private EvenChecker evenChecker;
    
    @BeforeEach
    void setUp() {
        evenChecker = new EvenChecker();
        System.out.println("Setting up EvenChecker for test...");
    }
    
    /**
     * Basic parameterized test using @ValueSource for even numbers
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10, 100, 1000, -2, -4, -6})
    @DisplayName("Should return true for even numbers")
    void testIsEven_EvenNumbers_ReturnsTrue(int number) {
        System.out.println("Testing even number: " + number);
        
        // Act & Assert
        boolean result = evenChecker.isEven(number);
        assertTrue(result, "Number " + number + " should be even");
        
        System.out.println("✓ " + number + " is correctly identified as even");
    }
    
    /**
     * Parameterized test using @ValueSource for odd numbers
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 11, 101, 999, -1, -3, -5})
    @DisplayName("Should return false for odd numbers")
    void testIsEven_OddNumbers_ReturnsFalse(int number) {
        System.out.println("Testing odd number: " + number);
        
        // Act & Assert
        boolean result = evenChecker.isEven(number);
        assertFalse(result, "Number " + number + " should be odd");
        
        System.out.println("✓ " + number + " is correctly identified as odd");
    }
    
    /**
     * Parameterized test using @CsvSource for number and expected result pairs
     */
    @ParameterizedTest
    @CsvSource({
        "0, true",
        "1, false", 
        "2, true",
        "3, false",
        "4, true",
        "5, false",
        "10, true",
        "15, false",
        "100, true",
        "101, false",
        "-2, true",
        "-3, false"
    })
    @DisplayName("Should correctly identify even/odd using CSV source")
    void testIsEven_CsvSource_CorrectResults(int number, boolean expected) {
        System.out.println("Testing number: " + number + ", expected: " + expected);
        
        // Act
        boolean result = evenChecker.isEven(number);
        
        // Assert
        assertEquals(expected, result, 
                    "Number " + number + " should return " + expected);
        
        System.out.println("✓ " + number + " correctly returned " + result);
    }
    
    /**
     * Parameterized test using @MethodSource for complex test data
     */
    @ParameterizedTest
    @MethodSource("provideNumbersForEvenTest")
    @DisplayName("Should handle various number scenarios using method source")
    void testIsEven_MethodSource_VariousScenarios(int number, boolean expectedEven, String description) {
        System.out.println("Testing: " + description + " (number: " + number + ")");
        
        // Act
        boolean result = evenChecker.isEven(number);
        
        // Assert
        assertEquals(expectedEven, result, description);
        
        System.out.println("✓ " + description + " - result: " + result);
    }
    
    /**
     * Method source for complex test scenarios
     */
    static Stream<Arguments> provideNumbersForEvenTest() {
        return Stream.of(
            Arguments.of(0, true, "Zero should be even"),
            Arguments.of(2, true, "Smallest positive even number"),
            Arguments.of(1, false, "Smallest positive odd number"),
            Arguments.of(-2, true, "Negative even number"),
            Arguments.of(-1, false, "Negative odd number"),
            Arguments.of(1000000, true, "Large even number"),
            Arguments.of(999999, false, "Large odd number"),
            Arguments.of(Integer.MAX_VALUE - 1, true, "Near max even value"),
            Arguments.of(Integer.MAX_VALUE, false, "Max integer value (odd)"),
            Arguments.of(Integer.MIN_VALUE, true, "Min integer value (even)")
        );
    }
    
    /**
     * Test for isOdd method using parameterized approach
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
    @DisplayName("Should return true for odd numbers in isOdd method")
    void testIsOdd_OddNumbers_ReturnsTrue(int number) {
        System.out.println("Testing isOdd with odd number: " + number);
        
        boolean result = evenChecker.isOdd(number);
        assertTrue(result, "Number " + number + " should be odd");
        
        System.out.println("✓ " + number + " is correctly identified as odd");
    }
    
    /**
     * Test for isPositiveEven method using CsvSource
     */
    @ParameterizedTest
    @CsvSource({
        "2, true",
        "4, true",
        "6, true",
        "8, true",
        "1, false",   // positive but odd
        "3, false",   // positive but odd
        "0, false",   // even but not positive
        "-2, false",  // even but negative
        "-4, false"   // even but negative
    })
    @DisplayName("Should correctly identify positive even numbers")
    void testIsPositiveEven_VariousNumbers_CorrectResults(int number, boolean expected) {
        System.out.println("Testing isPositiveEven with number: " + number);
        
        boolean result = evenChecker.isPositiveEven(number);
        assertEquals(expected, result, 
                    "Number " + number + " should return " + expected + " for isPositiveEven");
        
        System.out.println("✓ " + number + " correctly returned " + result + " for isPositiveEven");
    }
    
    /**
     * Test for getNextEven method using CsvSource
     */
    @ParameterizedTest
    @CsvSource({
        "1, 2",    // odd -> next even
        "2, 4",    // even -> next even
        "3, 4",    // odd -> next even
        "4, 6",    // even -> next even
        "5, 6",    // odd -> next even
        "0, 2",    // even -> next even
        "-1, 0",   // odd -> next even
        "-2, 0"    // even -> next even
    })
    @DisplayName("Should return correct next even number")
    void testGetNextEven_VariousInputs_CorrectNextEven(int input, int expectedNext) {
        System.out.println("Testing getNextEven: " + input + " -> expected: " + expectedNext);
        
        int result = evenChecker.getNextEven(input);
        assertEquals(expectedNext, result, 
                    "Next even after " + input + " should be " + expectedNext);
        
        System.out.println("✓ Next even after " + input + " is correctly " + result);
    }
    
    /**
     * Test for countEvensInRange method using MethodSource
     */
    @ParameterizedTest
    @MethodSource("provideRangesForCountEvens")
    @DisplayName("Should correctly count even numbers in range")
    void testCountEvensInRange_VariousRanges_CorrectCount(int start, int end, int expectedCount, String description) {
        System.out.println("Testing range [" + start + ", " + end + "]: " + description);
        
        int result = evenChecker.countEvensInRange(start, end);
        assertEquals(expectedCount, result, 
                    description + " - expected " + expectedCount + " evens in range [" + start + ", " + end + "]");
        
        System.out.println("✓ Range [" + start + ", " + end + "] has " + result + " even numbers");
    }
    
    /**
     * Method source for range testing
     */
    static Stream<Arguments> provideRangesForCountEvens() {
        return Stream.of(
            Arguments.of(1, 5, 2, "Range 1-5 should have 2 evens (2,4)"),
            Arguments.of(1, 10, 5, "Range 1-10 should have 5 evens (2,4,6,8,10)"),
            Arguments.of(0, 10, 6, "Range 0-10 should have 6 evens (0,2,4,6,8,10)"),
            Arguments.of(2, 8, 4, "Range 2-8 should have 4 evens (2,4,6,8)"),
            Arguments.of(1, 1, 0, "Range 1-1 should have 0 evens"),
            Arguments.of(2, 2, 1, "Range 2-2 should have 1 even"),
            Arguments.of(5, 3, 0, "Invalid range (start > end) should return 0"),
            Arguments.of(-4, 4, 5, "Range -4 to 4 should have 5 evens (-4,-2,0,2,4)")
        );
    }
    
    /**
     * Edge case testing using parameterized approach
     */
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE, 0})
    @DisplayName("Should handle edge cases correctly")
    void testIsEven_EdgeCases_HandlesCorrectly(int number) {
        System.out.println("Testing edge case: " + number);
        
        // Act
        boolean result = evenChecker.isEven(number);
        
        // Assert based on mathematical definition
        boolean expected = (number % 2 == 0);
        assertEquals(expected, result, "Edge case " + number + " should be handled correctly");
        
        System.out.println("✓ Edge case " + number + " correctly handled: " + result);
    }
    
    /**
     * Test demonstrating multiple assertions in parameterized test
     */
    @ParameterizedTest
    @CsvSource({
        "4, true, false, true",     // even, not odd, positive even
        "3, false, true, false",    // odd, is odd, not positive even
        "0, true, false, false",    // even, not odd, not positive even (zero)
        "-2, true, false, false"    // even, not odd, not positive even (negative)
    })
    @DisplayName("Should satisfy multiple conditions consistently")
    void testMultipleConditions_ConsistentResults(int number, boolean shouldBeEven, 
                                                  boolean shouldBeOdd, boolean shouldBePositiveEven) {
        System.out.println("Testing multiple conditions for number: " + number);
        
        // Test all related methods
        assertEquals(shouldBeEven, evenChecker.isEven(number), 
                    "isEven(" + number + ") should return " + shouldBeEven);
        assertEquals(shouldBeOdd, evenChecker.isOdd(number), 
                    "isOdd(" + number + ") should return " + shouldBeOdd);
        assertEquals(shouldBePositiveEven, evenChecker.isPositiveEven(number), 
                    "isPositiveEven(" + number + ") should return " + shouldBePositiveEven);
        
        // Verify that isEven and isOdd are opposites
        assertNotEquals(evenChecker.isEven(number), evenChecker.isOdd(number),
                       "isEven and isOdd should return opposite values for " + number);
        
        System.out.println("✓ All conditions consistent for " + number);
    }
}
