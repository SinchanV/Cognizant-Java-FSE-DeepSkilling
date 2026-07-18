package com.junit.testsuite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Calculator demonstrating test categorization with tags
 */
@DisplayName("Calculator Tests")
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    // Fast unit tests for basic operations
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.MATH)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Addition should work correctly")
    void testAddition() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-5, 5));
        assertEquals(-8, calculator.add(-3, -5));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.MATH)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Subtraction should work correctly")
    void testSubtraction() {
        assertEquals(2, calculator.subtract(5, 3));
        assertEquals(-10, calculator.subtract(-5, 5));
        assertEquals(2, calculator.subtract(-3, -5));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.MATH)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Multiplication should work correctly")
    void testMultiplication() {
        assertEquals(15, calculator.multiply(3, 5));
        assertEquals(-25, calculator.multiply(-5, 5));
        assertEquals(15, calculator.multiply(-3, -5));
        assertEquals(0, calculator.multiply(0, 100));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.MATH)
    @Tag(TestCategories.SMOKE)
    @DisplayName("Division should work correctly")
    void testDivision() {
        assertEquals(2.5, calculator.divide(5, 2), 0.001);
        assertEquals(-2.5, calculator.divide(-5, 2), 0.001);
        assertEquals(0, calculator.divide(0, 5), 0.001);
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.MATH)
    @Tag(TestCategories.EDGE_CASE)
    @DisplayName("Division by zero should throw exception")
    void testDivisionByZero() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> calculator.divide(10, 0)
        );
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.MATH)
    @DisplayName("Modulo should work correctly")
    void testModulo() {
        assertEquals(1, calculator.modulo(7, 3));
        assertEquals(0, calculator.modulo(10, 5));
        assertEquals(2, calculator.modulo(8, 3));
    }
    
    @Test
    @Tag(TestCategories.FAST)
    @Tag(TestCategories.MATH)
    @Tag(TestCategories.EDGE_CASE)
    @DisplayName("Modulo by zero should throw exception")
    void testModuloByZero() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> calculator.modulo(10, 0)
        );
        assertEquals("Cannot calculate modulo by zero", exception.getMessage());
    }
    
    @Test
    @Tag(TestCategories.SLOW)
    @Tag(TestCategories.MATH)
    @Tag(TestCategories.PERFORMANCE)
    @DisplayName("Power function should work correctly")
    void testPower() {
        assertEquals(8.0, calculator.power(2, 3), 0.001);
        assertEquals(1.0, calculator.power(5, 0), 0.001);
        assertEquals(0.25, calculator.power(2, -2), 0.001);
        assertEquals(27.0, calculator.power(3, 3), 0.001);
    }
    
    @Test
    @Tag(TestCategories.REGRESSION)
    @Tag(TestCategories.MATH)
    @DisplayName("Complex mathematical operations")
    void testComplexOperations() {
        // Test chaining operations
        int result = calculator.add(calculator.multiply(3, 4), calculator.subtract(10, 5));
        assertEquals(17, result);
        
        // Test with negative numbers
        double divResult = calculator.divide(calculator.subtract(0, 10), 2);
        assertEquals(-5.0, divResult, 0.001);
    }
    
    @Test
    @Tag(TestCategories.EDGE_CASE)
    @Tag(TestCategories.MATH)
    @DisplayName("Edge cases with large numbers")
    void testLargeNumbers() {
        assertEquals(Integer.MAX_VALUE, calculator.add(Integer.MAX_VALUE - 1, 1));
        assertEquals(Integer.MIN_VALUE, calculator.subtract(Integer.MIN_VALUE + 1, 1));
    }
}
