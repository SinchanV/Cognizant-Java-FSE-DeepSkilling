package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main Spring Boot Application Class
 * 
 * This class serves as the entry point for the Spring Boot application.
 * The @SpringBootApplication annotation is a convenience annotation that combines:
 * - @Configuration: Indicates that this class contains configuration
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration mechanism
 * - @ComponentScan: Enables component scanning in the current package and sub-packages
 */
@SpringBootApplication
public class SpringLearnApplication {

	// Logger instance for logging application events
	private static final Logger logger = LoggerFactory.getLogger(SpringLearnApplication.class);

	/**
	 * Main method - Entry point of the Spring Boot application
	 * 
	 * @param args command line arguments passed to the application
	 */
	public static void main(String[] args) {
		logger.info("=================================================================");
		logger.info("Starting Spring Learn Application...");
		logger.info("=================================================================");
		
		// Demonstrate Spring XML Configuration with SimpleDateFormat
		displayDate();
		
		// Start the Spring Boot application
		SpringApplication.run(SpringLearnApplication.class, args);
		
		logger.info("=================================================================");
		logger.info("Spring Learn Application started successfully!");
		logger.info("Application is running on: http://localhost:8080");
		logger.info("=================================================================");
	}

	/**
	 * Method to demonstrate Spring XML Configuration for SimpleDateFormat
	 * This method loads the date-format.xml configuration and uses the dateFormat bean
	 */
	public static void displayDate() {
		logger.info("=================================================================");
		logger.info("Demonstrating Spring XML Configuration with SimpleDateFormat");
		logger.info("=================================================================");
		
		try {
			// Create ApplicationContext from XML configuration
			ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
			
			// Get the dateFormat bean from Spring container
			SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
			
			// Parse the string '31/12/2018' to Date class
			String dateString = "31/12/2018";
			Date parsedDate = format.parse(dateString);
			
			// Display the result
			System.out.println("Original date string: " + dateString);
			System.out.println("Parsed Date object: " + parsedDate);
			System.out.println("Formatted back to string: " + format.format(parsedDate));
			
			logger.info("Successfully loaded SimpleDateFormat from Spring XML Configuration");
			logger.info("Date pattern used: dd/MM/yyyy");
			logger.info("Parsed date: " + parsedDate);
			
		} catch (Exception e) {
			logger.error("Error in displayDate() method: " + e.getMessage());
			e.printStackTrace();
		}
		
		logger.info("=================================================================");
	}
}
