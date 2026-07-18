package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Simple test class to verify Spring IoC Container configuration
 */
public class SpringIoCTest {

    public static void main(String[] args) {
        System.out.println("=== Spring IoC Container Test ===");
        
        try {
            // Load Spring context
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            System.out.println("✓ Spring context loaded successfully");
            
            // Get BookService bean
            BookService bookService = context.getBean("bookService", BookService.class);
            System.out.println("✓ BookService bean retrieved successfully");
            
            // Test dependency injection
            if (bookService.getBookRepository() != null) {
                System.out.println("✓ BookRepository dependency injected successfully");
            } else {
                System.out.println("✗ BookRepository dependency injection failed");
            }
            
            // Test basic functionality
            long bookCount = bookService.getTotalBooksCount();
            System.out.println("✓ BookService working - Total books: " + bookCount);
            
            System.out.println("\n=== Spring IoC Container Configuration SUCCESS ===");
            
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
