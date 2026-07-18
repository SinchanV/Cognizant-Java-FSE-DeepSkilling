package com.library;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for Spring Boot Application
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class LibraryManagementApplicationTests {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Test
    public void contextLoads() {
        assertNotNull(bookService);
        assertNotNull(bookRepository);
    }
    
    @Test
    public void testCreateBook() {
        Book book = new Book("Test Book", "Test Author", "978-1-234567-89-0", "Test", 19.99);
        Book savedBook = bookService.createBook(book);
        
        assertNotNull(savedBook.getId());
        assertEquals("Test Book", savedBook.getTitle());
        assertEquals("Test Author", savedBook.getAuthor());
    }
    
    @Test
    public void testFindAllBooks() {
        List<Book> books = bookService.getAllBooks();
        assertNotNull(books);
        assertTrue(books.size() >= 0);
    }
    
    @Test
    public void testSearchBooksByTitle() {
        // Create a test book first
        Book book = new Book("Spring Boot Guide", "Test Author", "978-1-234567-90-6", "Technology", 39.99);
        bookService.createBook(book);
        
        List<Book> books = bookService.searchBooksByTitle("Spring Boot");
        assertNotNull(books);
        assertTrue(books.size() > 0);
        assertTrue(books.stream().anyMatch(b -> b.getTitle().contains("Spring Boot")));
    }
    
    @Test
    public void testBookRepository() {
        Book book = new Book("Repository Test", "Test Author", "978-1-234567-91-3", "Test", 25.99);
        Book savedBook = bookRepository.save(book);
        
        Optional<Book> foundBook = bookRepository.findById(savedBook.getId());
        assertTrue(foundBook.isPresent());
        assertEquals("Repository Test", foundBook.get().getTitle());
    }
    
    @Test
    public void testFindByIsbn() {
        Book book = new Book("ISBN Test", "Test Author", "978-1-234567-92-0", "Test", 15.99);
        bookService.createBook(book);
        
        Optional<Book> foundBook = bookService.getBookByIsbn("978-1-234567-92-0");
        assertTrue(foundBook.isPresent());
        assertEquals("ISBN Test", foundBook.get().getTitle());
    }
}
