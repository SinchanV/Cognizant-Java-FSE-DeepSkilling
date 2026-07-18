package com.junit.order;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Demonstrates ordering tests by method name (alphabetical order)
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("Tests Ordered by Method Name")
public class MethodNameOrderedTests {
    
    @BeforeEach
    void setUp() {
        System.out.println("Setting up test...");
    }
    
    @Test
    void testZLast() {
        System.out.println("Executing: testZLast (should be last alphabetically)");
        assertTrue(true);
    }
    
    @Test
    void testAFirst() {
        System.out.println("Executing: testAFirst (should be first alphabetically)");
        assertTrue(true);
    }
    
    @Test
    void testMMiddle() {
        System.out.println("Executing: testMMiddle (should be in middle)");
        assertTrue(true);
    }
    
    @Test
    void test1Numeric() {
        System.out.println("Executing: test1Numeric (numbers come before letters)");
        assertTrue(true);
    }
}
