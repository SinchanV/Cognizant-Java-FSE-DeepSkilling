package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import java.util.List;

/**
 * Service class for managing Book operations with dependency injection
 */
public class BookService {
    
    private BookRepository bookRepository;

    // Default constructor
    public BookService() {
        System.out.println("BookService initialized");
    }

    // Setter method for dependency injection (required for Spring IoC)
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected into BookService");
    }

    // Getter for testing purposes
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public Book addBook(Book book) {
        System.out.println("BookService: Adding book - " + book.getTitle());
        return bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        System.out.println("BookService: Finding book by ID - " + id);
        return bookRepository.findById(id);
    }

    public List<Book> findAllBooks() {
        System.out.println("BookService: Retrieving all books");
        return bookRepository.findAll();
    }

    public List<Book> findBooksByTitle(String title) {
        System.out.println("BookService: Searching books by title - " + title);
        return bookRepository.findByTitle(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        System.out.println("BookService: Searching books by author - " + author);
        return bookRepository.findByAuthor(author);
    }

    public Book updateBook(Book book) {
        System.out.println("BookService: Updating book - " + book.getTitle());
        if (book.getId() != null && bookRepository.existsById(book.getId())) {
            return bookRepository.save(book);
        } else {
            System.out.println("BookService: Book not found for update");
            return null;
        }
    }

    public void deleteBook(Long id) {
        System.out.println("BookService: Deleting book with ID - " + id);
        bookRepository.deleteById(id);
    }

    public boolean isBookAvailable(Long id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            boolean available = book.isAvailable();
            System.out.println("BookService: Book availability check - " + available);
            return available;
        }
        System.out.println("BookService: Book not found for availability check");
        return false;
    }

    public long getTotalBooksCount() {
        System.out.println("BookService: Getting total books count");
        return bookRepository.count();
    }

    public void displayBookService() {
        System.out.println("BookService is working!");
        System.out.println("Repository injected: " + (bookRepository != null ? "Yes" : "No"));
        if (bookRepository != null) {
            System.out.println("Total books in repository: " + bookRepository.count());
        }
    }
}
