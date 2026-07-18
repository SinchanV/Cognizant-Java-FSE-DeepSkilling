package com.junit.testsuite;

import org.junit.jupiter.api.Tag;

/**
 * Test category interfaces for organizing tests
 * These are used with @Tag annotation to categorize tests
 */
public class TestCategories {
    
    /**
     * Tag for fast-running unit tests
     */
    public static final String FAST = "fast";
    
    /**
     * Tag for slow-running integration tests
     */
    public static final String SLOW = "slow";
    
    /**
     * Tag for smoke tests (basic functionality)
     */
    public static final String SMOKE = "smoke";
    
    /**
     * Tag for regression tests
     */
    public static final String REGRESSION = "regression";
    
    /**
     * Tag for edge case tests
     */
    public static final String EDGE_CASE = "edge-case";
    
    /**
     * Tag for mathematical operation tests
     */
    public static final String MATH = "math";
    
    /**
     * Tag for string processing tests
     */
    public static final String STRING = "string";
    
    /**
     * Tag for performance-related tests
     */
    public static final String PERFORMANCE = "performance";
}
