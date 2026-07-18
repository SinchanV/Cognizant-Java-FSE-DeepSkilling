package com.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PerformanceTester demonstrating various timeout and performance testing scenarios
 */
@DisplayName("Performance and Timeout Tests")
public class PerformanceTesterTest {

    private PerformanceTester performanceTester;

    @BeforeEach
    void setUp() {
        performanceTester = new PerformanceTester();
    }

    @Nested
    @DisplayName("Basic Timeout Tests")
    class BasicTimeoutTests {

        @Test
        @DisplayName("Fast task should complete within timeout")
        @Timeout(value = 1, unit = TimeUnit.SECONDS)
        void testFastTaskWithTimeout() {
            String result = performanceTester.performFastTask();
            assertEquals("Fast task completed", result);
        }

        @Test
        @DisplayName("Slow task should timeout")
        @Timeout(value = 2, unit = TimeUnit.SECONDS)
        void testSlowTaskTimeout() {
            // This test should fail due to timeout
            // The @Timeout annotation will cause a timeout exception
            performanceTester.performSlowTask();
        }

        @Test
        @DisplayName("Task with specific duration should complete within timeout")
        @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
        void testTaskWithDuration() {
            String result = performanceTester.performTask(200);
            assertEquals("Task completed in 200ms", result);
        }
    }

    @Nested
    @DisplayName("Advanced Timeout Tests")
    class AdvancedTimeoutTests {

        @Test
        @DisplayName("Test using assertTimeout method")
        void testUsingAssertTimeout() {
            // Test that completes within timeout
            assertTimeout(Duration.ofMillis(300), () -> {
                return performanceTester.performTask(200);
            });
        }

        @Test
        @DisplayName("Test using assertTimeoutPreemptively method")
        void testUsingAssertTimeoutPreemptively() {
            // This will interrupt the execution if it takes too long
            String result = assertTimeoutPreemptively(Duration.ofMillis(300), () -> {
                return performanceTester.performTask(100);
            });
            assertEquals("Task completed in 100ms", result);
        }

        @Test
        @DisplayName("Test timeout with custom message")
        void testTimeoutWithCustomMessage() {
            assertTimeout(Duration.ofMillis(500), 
                () -> performanceTester.performTask(200),
                "Task should complete within 500ms but took longer");
        }
    }

    @Nested
    @DisplayName("Performance Benchmarking Tests")
    class PerformanceBenchmarkingTests {

        @Test
        @DisplayName("CPU intensive task performance test")
        @Timeout(value = 5, unit = TimeUnit.SECONDS)
        void testCpuIntensiveTaskPerformance() {
            long startTime = System.nanoTime();
            
            long result = performanceTester.performCpuIntensiveTask(1000000);
            
            long endTime = System.nanoTime();
            long durationMs = (endTime - startTime) / 1_000_000;
            
            // Assert that the task completed and took reasonable time
            assertNotEquals(0, result);
            assertTrue(durationMs < 5000, "CPU intensive task should complete within 5 seconds");
            
            System.out.println("CPU intensive task completed in " + durationMs + "ms");
        }

        @RepeatedTest(3)
        @DisplayName("Repeated performance test")
        @Timeout(value = 1, unit = TimeUnit.SECONDS)
        void testRepeatedPerformance() {
            long startTime = System.currentTimeMillis();
            
            String result = performanceTester.performTask(200);
            
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            assertEquals("Task completed in 200ms", result);
            assertTrue(duration >= 200 && duration < 400, 
                "Task should take approximately 200ms but took " + duration + "ms");
        }
    }

    @Nested
    @DisplayName("Database Operation Simulation Tests")
    class DatabaseOperationTests {

        @Test
        @DisplayName("Simple database operation should complete quickly")
        @Timeout(value = 1, unit = TimeUnit.SECONDS)
        void testSimpleDatabaseOperation() {
            String result = performanceTester.simulateDatabaseOperation(1);
            assertEquals("Database operation completed with complexity level 1", result);
        }

        @Test
        @DisplayName("Complex database operation may take longer")
        @Timeout(value = 3, unit = TimeUnit.SECONDS)
        void testComplexDatabaseOperation() {
            String result = performanceTester.simulateDatabaseOperation(3);
            assertEquals("Database operation completed with complexity level 3", result);
        }

        @Test
        @DisplayName("Very complex database operation should timeout")
        void testVeryComplexDatabaseOperationTimeout() {
            // This should timeout as complexity 10 = 5 seconds
            assertThrows(org.opentest4j.AssertionFailedError.class, () -> {
                assertTimeout(Duration.ofSeconds(2), () -> {
                    return performanceTester.simulateDatabaseOperation(10);
                });
            });
        }
    }

    @Nested
    @DisplayName("Edge Case Timeout Tests")
    class EdgeCaseTimeoutTests {

        @Test
        @DisplayName("Test non-hanging task")
        @Timeout(value = 1, unit = TimeUnit.SECONDS)
        void testNonHangingTask() {
            String result = performanceTester.performPotentiallyHangingTask(false);
            assertEquals("Task completed successfully", result);
        }

        @Test
        @DisplayName("Test hanging task with timeout")
        void testHangingTaskTimeout() {
            // This test demonstrates timeout handling for hanging tasks
            assertThrows(org.opentest4j.AssertionFailedError.class, () -> {
                assertTimeoutPreemptively(Duration.ofMillis(500), () -> {
                    return performanceTester.performPotentiallyHangingTask(true);
                });
            });
        }
    }

    @Test
    @DisplayName("Global timeout test for entire test method")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGlobalTimeout() {
        // This test has an overall timeout of 10 seconds
        
        // Perform multiple operations
        performanceTester.performTask(100);
        performanceTester.performTask(200);
        performanceTester.performCpuIntensiveTask(10000);
        
        // All operations combined should complete within 10 seconds
        assertTrue(true, "All operations completed within global timeout");
    }
}
