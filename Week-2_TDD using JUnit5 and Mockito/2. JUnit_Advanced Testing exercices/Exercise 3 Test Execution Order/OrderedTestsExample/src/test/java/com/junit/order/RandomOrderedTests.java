package com.junit.order;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Demonstrates random test execution order (default behavior can be unpredictable)
 * This class shows tests that don't depend on execution order
 */
@TestMethodOrder(MethodOrderer.Random.class)
@DisplayName("Tests with Random Order")
public class RandomOrderedTests {
    
    @BeforeEach
    void setUp() {
        System.out.println("Setting up independent test...");
    }
    
    @Test
    @DisplayName("Independent Test 1")
    void independentTest1() {
        System.out.println("Executing: Independent Test 1");
        int result = 2 + 2;
        assertEquals(4, result);
    }
    
    @Test
    @DisplayName("Independent Test 2")
    void independentTest2() {
        System.out.println("Executing: Independent Test 2");
        String text = "hello".toUpperCase();
        assertEquals("HELLO", text);
    }
    
    @Test
    @DisplayName("Independent Test 3")
    void independentTest3() {
        System.out.println("Executing: Independent Test 3");
        boolean result = "test".contains("est");
        assertTrue(result);
    }
    
    @Test
    @DisplayName("Independent Test 4")
    void independentTest4() {
        System.out.println("Executing: Independent Test 4");
        double result = Math.PI;
        assertTrue(result > 3.0 && result < 4.0);
    }
    
    @Test
    @DisplayName("Independent Test 5")
    void independentTest5() {
        System.out.println("Executing: Independent Test 5");
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(5, array.length);
    }
}
