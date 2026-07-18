package com.library;

import com.library.entity.Book;
import com.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main Spring Boot Application Class
 */
@SpringBootApplication
public class LibraryManagementApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(LibraryManagementApplication.class);
    
    public static void main(String[] args) {
        logger.info("Starting Library Management Application...");
        SpringApplication.run(LibraryManagementApplication.class, args);
        logger.info("Library Management Application started successfully!");
    }
    
    /**
     * CommandLineRunner to initialize sample data
     */
    @Bean
    public CommandLineRunner initData(@Autowired BookService bookService) {
        return args -> {
            logger.info("Initializing sample data...");
            
            // Create sample books
            try {
                Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 
                                    "978-0-7432-7356-5", "Fiction", 12.99);
                book1.setPublisher("Scribner");
                book1.setDescription("A classic American novel");
                book1.setTotalCopies(5);
                book1.setAvailableCopies(5);
                bookService.createBook(book1);
                
                Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 
                                    "978-0-06-112008-4", "Fiction", 14.99);
                book2.setPublisher("J. B. Lippincott & Co.");
                book2.setDescription("A novel about serious issues of rape and racial inequality");
                book2.setTotalCopies(3);
                book2.setAvailableCopies(3);
                bookService.createBook(book2);
                
                Book book3 = new Book("1984", "George Orwell", 
                                    "978-0-452-28423-4", "Dystopian", 13.99);
                book3.setPublisher("Secker & Warburg");
                book3.setDescription("A dystopian social science fiction novel");
                book3.setTotalCopies(4);
                book3.setAvailableCopies(4);
                bookService.createBook(book3);
                
                Book book4 = new Book("Spring in Action", "Craig Walls", 
                                    "978-1-935182-35-1", "Technology", 49.99);
                book4.setPublisher("Manning Publications");
                book4.setDescription("Comprehensive guide to Spring Framework");
                book4.setTotalCopies(2);
                book4.setAvailableCopies(2);
                bookService.createBook(book4);
                
                Book book5 = new Book("Effective Java", "Joshua Bloch", 
                                    "978-0-321-35668-0", "Technology", 45.99);
                book5.setPublisher("Addison-Wesley");
                book5.setDescription("Best practices for Java programming language");
                book5.setTotalCopies(3);
                book5.setAvailableCopies(3);
                bookService.createBook(book5);
                
                logger.info("Sample data initialization completed successfully!");
                logger.info("Total books created: {}", bookService.getTotalBookCount());
                
            } catch (Exception e) {
                logger.error("Error initializing sample data: {}", e.getMessage());
            }
            
            // Display application info
            logger.info("=== Library Management Application Info ===");
            logger.info("Application: Library Management System");
            logger.info("Version: 1.0.0");
            logger.info("Server Port: 8080");
            logger.info("H2 Console: http://localhost:8080/h2-console");
            logger.info("API Base URL: http://localhost:8080/api/books");
            logger.info("===========================================");
        };
    }
}
