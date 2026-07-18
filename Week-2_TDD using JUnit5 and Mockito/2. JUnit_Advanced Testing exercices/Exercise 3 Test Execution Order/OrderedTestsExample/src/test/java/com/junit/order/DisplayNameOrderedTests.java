package com.junit.order;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Demonstrates ordering tests by display name (alphabetical order)
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Tests Ordered by Display Name")
public class DisplayNameOrderedTests {
    
    @BeforeEach
    void setUp() {
        System.out.println("Setting up test...");
    }
    
    @Test
    @DisplayName("A - First test alphabetically")
    void testA() {
        System.out.println("Executing: A - First test");
        assertTrue(true);
    }
    
    @Test
    @DisplayName("C - Third test alphabetically")
    void testC() {
        System.out.println("Executing: C - Third test");
        assertTrue(true);
    }
    
    @Test
    @DisplayName("B - Second test alphabetically")
    void testB() {
        System.out.println("Executing: B - Second test");
        assertTrue(true);
    }
    
    @Test
    @DisplayName("D - Fourth test alphabetically")
    void testD() {
        System.out.println("Executing: D - Fourth test");
        assertTrue(true);
    }
}
