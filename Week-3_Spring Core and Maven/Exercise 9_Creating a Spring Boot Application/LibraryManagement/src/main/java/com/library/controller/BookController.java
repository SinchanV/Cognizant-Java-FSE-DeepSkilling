package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST Controller for Book operations
 */
@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
        logger.info("BookController initialized");
    }
    
    // Create a new book
    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {
        try {
            logger.info("REST: Creating new book - {}", book.getTitle());
            Book createdBook = bookService.createBook(book);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating book: {}", e.getMessage());
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        logger.info("REST: Retrieving all books");
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        logger.info("REST: Retrieving book with ID: {}", id);
        Optional<Book> book = bookService.getBookById(id);
        
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(createErrorResponse("Book not found with ID: " + id), 
                                      HttpStatus.NOT_FOUND);
        }
    }
    
    // Update book
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        try {
            logger.info("REST: Updating book with ID: {}", id);
            Book updatedBook = bookService.updateBook(id, bookDetails);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating book: {}", e.getMessage());
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    
    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            logger.info("REST: Deleting book with ID: {}", id);
            bookService.deleteBook(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Book deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting book: {}", e.getMessage());
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    
    // Search books by title
    @GetMapping("/search/title")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        logger.info("REST: Searching books by title: {}", title);
        List<Book> books = bookService.searchBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    // Search books by author
    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> searchBooksByAuthor(@RequestParam String author) {
        logger.info("REST: Searching books by author: {}", author);
        List<Book> books = bookService.searchBooksByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    // Get books by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
        logger.info("REST: Retrieving books by category: {}", category);
        List<Book> books = bookService.getBooksByCategory(category);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    // Get available books
    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        logger.info("REST: Retrieving available books");
        List<Book> books = bookService.getAvailableBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    // Get books by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Book>> getBooksByPriceRange(
            @RequestParam Double minPrice, 
            @RequestParam Double maxPrice) {
        logger.info("REST: Retrieving books with price between {} and {}", minPrice, maxPrice);
        List<Book> books = bookService.getBooksByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    // Get book by ISBN
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        logger.info("REST: Retrieving book by ISBN: {}", isbn);
        Optional<Book> book = bookService.getBookByIsbn(isbn);
        
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(createErrorResponse("Book not found with ISBN: " + isbn), 
                                      HttpStatus.NOT_FOUND);
        }
    }
    
    // Get book statistics
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getBookStatistics() {
        logger.info("REST: Retrieving book statistics");
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBooks", bookService.getTotalBookCount());
        stats.put("availableBooks", bookService.getAvailableBookCount());
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
    
    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "Book Service");
        health.put("timestamp", java.time.LocalDateTime.now().toString());
        return new ResponseEntity<>(health, HttpStatus.OK);
    }
    
    // Helper method to create error response
    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        error.put("timestamp", java.time.LocalDateTime.now().toString());
        return error;
    }
}
