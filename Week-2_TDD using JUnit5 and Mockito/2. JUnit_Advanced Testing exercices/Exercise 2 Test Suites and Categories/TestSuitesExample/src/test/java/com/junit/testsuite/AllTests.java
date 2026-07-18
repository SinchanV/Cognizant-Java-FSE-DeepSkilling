package com.junit.testsuite;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.ExcludeTags;

/**
 * Test Suite that runs all test classes
 * Demonstrates different ways to organize and run test suites
 */
@Suite
@SelectClasses({
    CalculatorTest.class,
    StringProcessorTest.class
})
public class AllTests {
    // This class remains empty
    // It serves as a holder for the suite configuration annotations
}
