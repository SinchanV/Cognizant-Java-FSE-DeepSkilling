package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 * Main application class to demonstrate Constructor and Setter Injection
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {
        System.out.println("=== Library Management Application - Constructor and Setter Injection Demo ===");
        System.out.println();

        try {
            // Load Spring Application Context
            System.out.println("Loading Spring Application Context...");
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            System.out.println("Spring Application Context loaded successfully!");
            System.out.println();

            // Test Constructor Injection (single parameter)
            System.out.println("=== Testing Constructor Injection (Single Parameter) ===");
            BookService constructorService = context.getBean("bookServiceConstructor", BookService.class);
            constructorService.displayServiceInfo();
            testBookService(constructorService, "Constructor Injection");

            // Test Constructor Injection (multiple parameters)
            System.out.println("=== Testing Constructor Injection (Multiple Parameters) ===");
            BookService constructorMultipleService = context.getBean("bookServiceConstructorMultiple", BookService.class);
            constructorMultipleService.displayServiceInfo();
            testBookService(constructorMultipleService, "Constructor Injection (Multiple)");

            // Test Setter Injection
            System.out.println("=== Testing Setter Injection ===");
            BookService setterService = context.getBean("bookServiceSetter", BookService.class);
            setterService.displayServiceInfo();
            testBookService(setterService, "Setter Injection");

            // Test Mixed Injection (Constructor + Setter)
            System.out.println("=== Testing Mixed Injection (Constructor + Setter) ===");
            BookService mixedService = context.getBean("bookServiceMixed", BookService.class);
            mixedService.displayServiceInfo();
            testBookService(mixedService, "Mixed Injection");

            // Test Index-based Constructor Injection
            System.out.println("=== Testing Index-based Constructor Injection ===");
            BookService indexedService = context.getBean("bookServiceConstructorIndexed", BookService.class);
            indexedService.displayServiceInfo();

            // Test Type-based Constructor Injection
            System.out.println("=== Testing Type-based Constructor Injection ===");
            BookService typedService = context.getBean("bookServiceConstructorTyped", BookService.class);
            typedService.displayServiceInfo();

            // Comparison Summary
            System.out.println("=== Injection Types Comparison ===");
            System.out.println("1. Constructor Injection:");
            System.out.println("   ✓ Ensures mandatory dependencies are provided");
            System.out.println("   ✓ Creates immutable objects");
            System.out.println("   ✓ Fails fast if dependencies are missing");
            System.out.println();

            System.out.println("2. Setter Injection:");
            System.out.println("   ✓ Allows optional dependencies");
            System.out.println("   ✓ Enables partial dependency injection");
            System.out.println("   ✓ Supports reconfiguration after object creation");
            System.out.println();

            System.out.println("3. Mixed Injection:");
            System.out.println("   ✓ Combines benefits of both approaches");
            System.out.println("   ✓ Constructor for mandatory, setter for optional");
            System.out.println("   ✓ Maximum flexibility in configuration");
            System.out.println();

            System.out.println("=== Application completed successfully! ===");

        } catch (Exception e) {
            System.err.println("Error occurred while running the application:");
            e.printStackTrace();
        }
    }

    private static void testBookService(BookService bookService, String injectionType) {
        System.out.println("Testing " + injectionType + " BookService functionality:");
        
        // Test basic operations
        List<Book> allBooks = bookService.findAllBooks();
        System.out.println("Total books: " + allBooks.size());
        
        // Add a new book
        Book newBook = new Book("Design Patterns", "Gang of Four", "978-0-201-63361-0", 
                              "Technology", 54.99);
        bookService.addBook(newBook);
        
        // Search for books
        List<Book> technologyBooks = bookService.findBooksByTitle("Design");
        System.out.println("Found " + technologyBooks.size() + " books matching 'Design'");
        
        System.out.println(injectionType + " test completed successfully!\n");
    }
}
