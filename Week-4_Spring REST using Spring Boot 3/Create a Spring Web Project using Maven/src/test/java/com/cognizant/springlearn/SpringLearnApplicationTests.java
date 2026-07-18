package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration test for Spring Learn Application
 */
@SpringBootTest
class SpringLearnApplicationTests {

	/**
	 * Test to verify the Spring context loads successfully
	 */
	@Test
	void contextLoads() {
		// This test will pass if the Spring context loads without errors
	}

	/**
	 * Test to verify the main application class
	 */
	@Test
	void main() {
		// Test that the main method can be invoked without errors
		SpringLearnApplication.main(new String[] {});
	}
}
