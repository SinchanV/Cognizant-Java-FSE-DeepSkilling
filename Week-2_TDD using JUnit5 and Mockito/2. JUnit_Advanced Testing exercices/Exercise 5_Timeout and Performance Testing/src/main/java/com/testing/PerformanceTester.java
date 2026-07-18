package com.testing;

import java.util.concurrent.TimeUnit;

/**
 * PerformanceTester class demonstrates various performance scenarios
 * for timeout and performance testing with JUnit 5
 */
public class PerformanceTester {

    /**
     * A method that simulates a fast task
     * @return result of the task
     */
    public String performFastTask() {
        try {
            // Simulate a fast operation (100ms)
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task interrupted", e);
        }
        return "Fast task completed";
    }

    /**
     * A method that simulates a slow task
     * @return result of the task
     */
    public String performSlowTask() {
        try {
            // Simulate a slow operation (3 seconds)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task interrupted", e);
        }
        return "Slow task completed";
    }

    /**
     * A method that performs a task with configurable duration
     * @param durationMillis duration in milliseconds
     * @return result of the task
     */
    public String performTask(long durationMillis) {
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task interrupted", e);
        }
        return "Task completed in " + durationMillis + "ms";
    }

    /**
     * A method that performs CPU-intensive work
     * @param iterations number of iterations to perform
     * @return result of the computation
     */
    public long performCpuIntensiveTask(int iterations) {
        long result = 0;
        for (int i = 0; i < iterations; i++) {
            result += Math.sqrt(i) * Math.sin(i);
        }
        return (long) result;
    }

    /**
     * A method that may timeout due to infinite loop (for testing purposes)
     * @param shouldTimeout if true, creates an infinite loop
     * @return result string
     */
    public String performPotentiallyHangingTask(boolean shouldTimeout) {
        if (shouldTimeout) {
            // Infinite loop to simulate hanging
            while (true) {
                // This will cause timeout
            }
        }
        return "Task completed successfully";
    }

    /**
     * A method that simulates database operation with variable performance
     * @param complexity complexity level affecting execution time
     * @return result of database operation
     */
    public String simulateDatabaseOperation(int complexity) {
        try {
            // Simulate database operation time based on complexity
            long sleepTime = complexity * 500; // 500ms per complexity level
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Database operation interrupted", e);
        }
        return "Database operation completed with complexity level " + complexity;
    }
}
