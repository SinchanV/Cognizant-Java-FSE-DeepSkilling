package com.junit.testsuite;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.IncludeTags;

/**
 * Test Suite that runs only fast tests
 * Demonstrates tag-based test selection
 */
@Suite
@SelectPackages("com.junit.testsuite")
@IncludeTags(TestCategories.FAST)
public class FastTestSuite {
    // This class remains empty
    // It serves as a holder for the suite configuration annotations
}
