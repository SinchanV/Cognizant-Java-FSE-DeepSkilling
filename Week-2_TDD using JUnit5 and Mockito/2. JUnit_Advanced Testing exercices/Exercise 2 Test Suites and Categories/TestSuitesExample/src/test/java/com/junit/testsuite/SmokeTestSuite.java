package com.junit.testsuite;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.IncludeTags;

/**
 * Test Suite that runs only smoke tests
 * Demonstrates tag-based test selection for critical functionality
 */
@Suite
@SelectPackages("com.junit.testsuite")
@IncludeTags(TestCategories.SMOKE)
public class SmokeTestSuite {
    // This class remains empty
    // It serves as a holder for the suite configuration annotations
}
