package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Book operations
 */
@Service
@Transactional
public class BookService {
    
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        logger.info("BookService initialized");
    }
    
    // Create a new book
    public Book createBook(Book book) {
        logger.info("Creating new book: {}", book.getTitle());
        
        // Check if book with same ISBN already exists
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        
        Book savedBook = bookRepository.save(book);
        logger.info("Book created successfully with ID: {}", savedBook.getId());
        return savedBook;
    }
    
    // Get all books
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        logger.info("Retrieving all books");
        List<Book> books = bookRepository.findAll();
        logger.info("Retrieved {} books", books.size());
        return books;
    }
    
    // Get book by ID
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        logger.info("Retrieving book with ID: {}", id);
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            logger.info("Book found: {}", book.get().getTitle());
        } else {
            logger.warn("Book not found with ID: {}", id);
        }
        return book;
    }
    
    // Update book
    public Book updateBook(Long id, Book bookDetails) {
        logger.info("Updating book with ID: {}", id);
        
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setIsbn(bookDetails.getIsbn());
                    book.setCategory(bookDetails.getCategory());
                    book.setPrice(bookDetails.getPrice());
                    book.setPublisher(bookDetails.getPublisher());
                    book.setDescription(bookDetails.getDescription());
                    book.setAvailable(bookDetails.getAvailable());
                    book.setTotalCopies(bookDetails.getTotalCopies());
                    book.setAvailableCopies(bookDetails.getAvailableCopies());
                    
                    Book updatedBook = bookRepository.save(book);
                    logger.info("Book updated successfully: {}", updatedBook.getTitle());
                    return updatedBook;
                })
                .orElseThrow(() -> {
                    logger.error("Book not found with ID: {}", id);
                    return new RuntimeException("Book not found with ID: " + id);
                });
    }
    
    // Delete book
    public void deleteBook(Long id) {
        logger.info("Deleting book with ID: {}", id);
        
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            logger.info("Book deleted successfully with ID: {}", id);
        } else {
            logger.error("Book not found with ID: {}", id);
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }
    
    // Search books by title
    @Transactional(readOnly = true)
    public List<Book> searchBooksByTitle(String title) {
        logger.info("Searching books by title: {}", title);
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        logger.info("Found {} books matching title: {}", books.size(), title);
        return books;
    }
    
    // Search books by author
    @Transactional(readOnly = true)
    public List<Book> searchBooksByAuthor(String author) {
        logger.info("Searching books by author: {}", author);
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        logger.info("Found {} books by author: {}", books.size(), author);
        return books;
    }
    
    // Get books by category
    @Transactional(readOnly = true)
    public List<Book> getBooksByCategory(String category) {
        logger.info("Retrieving books by category: {}", category);
        List<Book> books = bookRepository.findByCategoryIgnoreCase(category);
        logger.info("Found {} books in category: {}", books.size(), category);
        return books;
    }
    
    // Get available books
    @Transactional(readOnly = true)
    public List<Book> getAvailableBooks() {
        logger.info("Retrieving available books");
        List<Book> books = bookRepository.findByAvailableTrue();
        logger.info("Found {} available books", books.size());
        return books;
    }
    
    // Get books by price range
    @Transactional(readOnly = true)
    public List<Book> getBooksByPriceRange(Double minPrice, Double maxPrice) {
        logger.info("Retrieving books with price between {} and {}", minPrice, maxPrice);
        List<Book> books = bookRepository.findByPriceBetween(minPrice, maxPrice);
        logger.info("Found {} books in price range", books.size());
        return books;
    }
    
    // Get book by ISBN
    @Transactional(readOnly = true)
    public Optional<Book> getBookByIsbn(String isbn) {
        logger.info("Retrieving book by ISBN: {}", isbn);
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            logger.info("Book found with ISBN: {}", isbn);
        } else {
            logger.warn("Book not found with ISBN: {}", isbn);
        }
        return book;
    }
    
    // Get total book count
    @Transactional(readOnly = true)
    public long getTotalBookCount() {
        long count = bookRepository.count();
        logger.info("Total books count: {}", count);
        return count;
    }
    
    // Get available book count
    @Transactional(readOnly = true)
    public long getAvailableBookCount() {
        long count = bookRepository.countAvailableBooks();
        logger.info("Available books count: {}", count);
        return count;
    }
}
