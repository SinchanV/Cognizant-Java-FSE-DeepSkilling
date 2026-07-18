package com.junit.exception;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.junit.exception.ExceptionThrower.InvalidAgeException;

/**
 * ExceptionThrowerTest class demonstrating various exception testing techniques in JUnit 5
 */
@DisplayName("Exception Testing Examples")
public class ExceptionThrowerTest {
    
    private ExceptionThrower exceptionThrower;
    
    @BeforeEach
    void setUp() {
        exceptionThrower = new ExceptionThrower();
        System.out.println("Setting up test for exception testing...");
    }
    
    @AfterEach
    void tearDown() {
        System.out.println("Test completed.\n");
    }
    
    // 1. Basic Exception Testing using assertThrows
    @Test
    @DisplayName("Test basic throwException method")
    void testThrowException() {
        System.out.println("Testing basic exception throwing...");
        
        // Test that the method throws RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exceptionThrower.throwException();
        });
        
        // Verify the exception message
        assertEquals("This is a runtime exception", exception.getMessage());
        System.out.println("✓ RuntimeException thrown with correct message");
    }
    
    // 2. Testing IllegalArgumentException
    @Test
    @DisplayName("Test validateInput with null input")
    void testValidateInputWithNull() {
        System.out.println("Testing validateInput with null input...");
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            exceptionThrower.validateInput(null);
        });
        
        assertEquals("Input cannot be null", exception.getMessage());
        System.out.println("✓ IllegalArgumentException thrown for null input");
    }
    
    @Test
    @DisplayName("Test validateInput with empty input")
    void testValidateInputWithEmpty() {
        System.out.println("Testing validateInput with empty input...");
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            exceptionThrower.validateInput("   "); // Empty/whitespace string
        });
        
        assertEquals("Input cannot be empty", exception.getMessage());
        System.out.println("✓ IllegalArgumentException thrown for empty input");
    }
    
    @Test
    @DisplayName("Test validateInput with valid input (no exception)")
    void testValidateInputWithValidInput() {
        System.out.println("Testing validateInput with valid input...");
        
        // This should NOT throw an exception
        assertDoesNotThrow(() -> {
            exceptionThrower.validateInput("Valid input");
        });
        
        System.out.println("✓ No exception thrown for valid input");
    }
    
    // 3. Testing IllegalStateException
    @Test
    @DisplayName("Test checkState with invalid state")
    void testCheckStateInvalid() {
        System.out.println("Testing checkState with invalid state...");
        
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            exceptionThrower.checkState(false);
        });
        
        assertEquals("Invalid state detected", exception.getMessage());
        System.out.println("✓ IllegalStateException thrown for invalid state");
    }
    
    @Test
    @DisplayName("Test checkState with valid state")
    void testCheckStateValid() {
        System.out.println("Testing checkState with valid state...");
        
        assertDoesNotThrow(() -> {
            exceptionThrower.checkState(true);
        });
        
        System.out.println("✓ No exception thrown for valid state");
    }
    
    // 4. Testing ArithmeticException
    @Test
    @DisplayName("Test divide by zero")
    void testDivideByZero() {
        System.out.println("Testing division by zero...");
        
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            exceptionThrower.divide(10, 0);
        });
        
        assertEquals("Division by zero is not allowed", exception.getMessage());
        System.out.println("✓ ArithmeticException thrown for division by zero");
    }
    
    @Test
    @DisplayName("Test valid division")
    void testValidDivision() {
        System.out.println("Testing valid division...");
        
        int result = assertDoesNotThrow(() -> {
            return exceptionThrower.divide(10, 2);
        });
        
        assertEquals(5, result);
        System.out.println("✓ Valid division completed: 10/2 = " + result);
    }
    
    // 5. Testing NumberFormatException
    @Test
    @DisplayName("Test parseNumber with invalid format")
    void testParseNumberInvalidFormat() {
        System.out.println("Testing parseNumber with invalid format...");
        
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            exceptionThrower.parseNumber("abc123");
        });
        
        assertTrue(exception.getMessage().contains("Invalid number format"));
        System.out.println("✓ NumberFormatException thrown for invalid format");
    }
    
    @Test
    @DisplayName("Test parseNumber with null input")
    void testParseNumberWithNull() {
        System.out.println("Testing parseNumber with null input...");
        
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            exceptionThrower.parseNumber(null);
        });
        
        assertEquals("Number string cannot be null or empty", exception.getMessage());
        System.out.println("✓ NumberFormatException thrown for null input");
    }
    
    @Test
    @DisplayName("Test parseNumber with valid input")
    void testParseNumberValid() {
        System.out.println("Testing parseNumber with valid input...");
        
        int result = assertDoesNotThrow(() -> {
            return exceptionThrower.parseNumber("123");
        });
        
        assertEquals(123, result);
        System.out.println("✓ Valid number parsed: " + result);
    }
    
    // 6. Testing IndexOutOfBoundsException
    @Test
    @DisplayName("Test getArrayElement with invalid index")
    void testGetArrayElementInvalidIndex() {
        System.out.println("Testing getArrayElement with invalid index...");
        
        String[] array = {"a", "b", "c"};
        
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            exceptionThrower.getArrayElement(array, 5);
        });
        
        assertTrue(exception.getMessage().contains("Index 5 is out of bounds"));
        System.out.println("✓ IndexOutOfBoundsException thrown for invalid index");
    }
    
    @Test
    @DisplayName("Test getArrayElement with null array")
    void testGetArrayElementNullArray() {
        System.out.println("Testing getArrayElement with null array...");
        
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            exceptionThrower.getArrayElement(null, 0);
        });
        
        assertEquals("Array cannot be null", exception.getMessage());
        System.out.println("✓ NullPointerException thrown for null array");
    }
    
    @Test
    @DisplayName("Test getArrayElement with valid index")
    void testGetArrayElementValid() {
        System.out.println("Testing getArrayElement with valid index...");
        
        String[] array = {"first", "second", "third"};
        
        String result = assertDoesNotThrow(() -> {
            return exceptionThrower.getArrayElement(array, 1);
        });
        
        assertEquals("second", result);
        System.out.println("✓ Valid array element retrieved: " + result);
    }
    
    // 7. Testing Custom Exception
    @Test
    @DisplayName("Test processAge with negative age")
    void testProcessAgeNegative() {
        System.out.println("Testing processAge with negative age...");
        
        InvalidAgeException exception = assertThrows(InvalidAgeException.class, () -> {
            exceptionThrower.processAge(-5);
        });
        
        assertTrue(exception.getMessage().contains("Age cannot be negative"));
        System.out.println("✓ InvalidAgeException thrown for negative age");
    }
    
    @Test
    @DisplayName("Test processAge with age too high")
    void testProcessAgeTooHigh() {
        System.out.println("Testing processAge with age too high...");
        
        InvalidAgeException exception = assertThrows(InvalidAgeException.class, () -> {
            exceptionThrower.processAge(200);
        });
        
        assertTrue(exception.getMessage().contains("Age cannot be greater than 150"));
        System.out.println("✓ InvalidAgeException thrown for age too high");
    }
    
    @Test
    @DisplayName("Test processAge with valid age")
    void testProcessAgeValid() {
        System.out.println("Testing processAge with valid age...");
        
        assertDoesNotThrow(() -> {
            exceptionThrower.processAge(25);
        });
        
        System.out.println("✓ No exception thrown for valid age");
    }
    
    // 8. Testing Conditional Exceptions
    @Test
    @DisplayName("Test processData that should throw exception")
    void testProcessDataThrowException() {
        System.out.println("Testing processData that should throw exception...");
        
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            exceptionThrower.processData("test", true);
        });
        
        assertEquals("Operation not supported", exception.getMessage());
        System.out.println("✓ UnsupportedOperationException thrown as expected");
    }
    
    @Test
    @DisplayName("Test processData that should not throw exception")
    void testProcessDataNoException() {
        System.out.println("Testing processData that should not throw exception...");
        
        String result = assertDoesNotThrow(() -> {
            return exceptionThrower.processData("test", false);
        });
        
        assertEquals("TEST", result);
        System.out.println("✓ No exception thrown, result: " + result);
    }
    
    // 9. Testing Multiple Exception Scenarios
    @Test
    @DisplayName("Test multiple exception scenarios - NullPointerException")
    void testMultipleExceptionScenariosNull() {
        System.out.println("Testing multiple exception scenarios - null...");
        
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            exceptionThrower.multipleExceptionScenarios("null");
        });
        
        assertEquals("Null scenario triggered", exception.getMessage());
        System.out.println("✓ NullPointerException thrown for null scenario");
    }
    
    @Test
    @DisplayName("Test multiple exception scenarios - IllegalArgumentException")
    void testMultipleExceptionScenariosIllegal() {
        System.out.println("Testing multiple exception scenarios - illegal...");
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            exceptionThrower.multipleExceptionScenarios("illegal");
        });
        
        assertEquals("Illegal argument scenario triggered", exception.getMessage());
        System.out.println("✓ IllegalArgumentException thrown for illegal scenario");
    }
    
    @Test
    @DisplayName("Test multiple exception scenarios - no exception")
    void testMultipleExceptionScenariosNoException() {
        System.out.println("Testing multiple exception scenarios - normal...");
        
        assertDoesNotThrow(() -> {
            exceptionThrower.multipleExceptionScenarios("normal");
        });
        
        System.out.println("✓ No exception thrown for normal scenario");
    }
    
    // 10. Advanced Exception Testing - Testing Exception Hierarchy
    @Test
    @DisplayName("Test exception hierarchy - RuntimeException parent")
    void testExceptionHierarchy() {
        System.out.println("Testing exception hierarchy...");
        
        // InvalidAgeException extends RuntimeException, so this should work
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exceptionThrower.processAge(-1);
        });
        
        // Verify it's actually our custom exception
        assertTrue(exception instanceof InvalidAgeException);
        System.out.println("✓ Exception hierarchy test passed");
    }
    
    // 11. Testing Exception with assertAll for multiple assertions
    @Test
    @DisplayName("Test multiple exceptions with assertAll")
    void testMultipleExceptionsWithAssertAll() {
        System.out.println("Testing multiple exceptions with assertAll...");
        
        assertAll(
            "Multiple exception scenarios",
            () -> {
                IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> {
                    exceptionThrower.validateInput(null);
                });
                assertEquals("Input cannot be null", ex1.getMessage());
            },
            () -> {
                ArithmeticException ex2 = assertThrows(ArithmeticException.class, () -> {
                    exceptionThrower.divide(1, 0);
                });
                assertEquals("Division by zero is not allowed", ex2.getMessage());
            },
            () -> {
                InvalidAgeException ex3 = assertThrows(InvalidAgeException.class, () -> {
                    exceptionThrower.processAge(-1);
                });
                assertTrue(ex3.getMessage().contains("Age cannot be negative"));
            }
        );
        
        System.out.println("✓ All exception assertions passed");
    }
}
