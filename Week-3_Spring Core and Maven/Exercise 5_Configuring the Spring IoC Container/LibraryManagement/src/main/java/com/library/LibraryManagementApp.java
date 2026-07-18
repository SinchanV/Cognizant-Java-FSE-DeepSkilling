package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 * Main application class to demonstrate Spring IoC Container configuration
 */
public class LibraryManagementApp {

    public static void main(String[] args) {
        System.out.println("=== Library Management Application - Spring IoC Container Demo ===");
        System.out.println();

        try {
            // Load Spring Application Context from XML configuration
            System.out.println("Loading Spring Application Context...");
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            System.out.println("Spring Application Context loaded successfully!");
            System.out.println();

            // Get BookService bean from Spring container
            System.out.println("Retrieving BookService bean from Spring container...");
            BookService bookService = (BookService) context.getBean("bookService");
            System.out.println("BookService bean retrieved successfully!");
            System.out.println();

            // Test the BookService functionality
            System.out.println("=== Testing BookService Functionality ===");
            bookService.displayBookService();
            System.out.println();

            // Display all books
            System.out.println("=== Displaying All Books ===");
            List<Book> allBooks = bookService.findAllBooks();
            for (Book book : allBooks) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + 
                                 " (ISBN: " + book.getIsbn() + ", Price: $" + book.getPrice() + ")");
            }
            System.out.println();

            // Add a new book
            System.out.println("=== Adding a New Book ===");
            Book newBook = new Book("Effective Java", "Joshua Bloch", "978-0-321-35668-0", 
                                  "Technology", 45.99);
            bookService.addBook(newBook);
            System.out.println("New book added successfully!");
            System.out.println();

            // Search for books by title
            System.out.println("=== Searching Books by Title ===");
            List<Book> javaBooks = bookService.findBooksByTitle("Java");
            for (Book book : javaBooks) {
                System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor());
            }
            System.out.println();

            // Search for books by author
            System.out.println("=== Searching Books by Author ===");
            List<Book> authorBooks = bookService.findBooksByAuthor("George");
            for (Book book : authorBooks) {
                System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor());
            }
            System.out.println();

            // Get total books count
            System.out.println("=== Total Books Count ===");
            long totalBooks = bookService.getTotalBooksCount();
            System.out.println("Total books in library: " + totalBooks);
            System.out.println();

            // Test book availability
            System.out.println("=== Testing Book Availability ===");
            boolean available = bookService.isBookAvailable(1L);
            System.out.println("Book with ID 1 is available: " + available);
            System.out.println();

            // Demonstrate Spring IoC Container benefits
            System.out.println("=== Spring IoC Container Benefits Demonstrated ===");
            System.out.println("✓ Dependency Injection: BookRepository automatically injected into BookService");
            System.out.println("✓ Bean Management: Spring manages object lifecycle and dependencies");
            System.out.println("✓ Configuration: All dependencies configured via XML without hard-coding");
            System.out.println("✓ Loose Coupling: Service and Repository are loosely coupled through interface");
            
            System.out.println();
            System.out.println("=== Application completed successfully! ===");

        } catch (Exception e) {
            System.err.println("Error occurred while running the application:");
            e.printStackTrace();
        }
    }
}
