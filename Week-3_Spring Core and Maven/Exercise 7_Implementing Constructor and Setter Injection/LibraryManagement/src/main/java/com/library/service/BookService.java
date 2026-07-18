package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import java.util.List;

/**
 * Service class demonstrating both Constructor and Setter Injection
 */
public class BookService {
    
    private BookRepository bookRepository;
    private String serviceName;
    private boolean enableLogging;

    // Default constructor (for setter injection)
    public BookService() {
        System.out.println("BookService: Default constructor called");
        this.serviceName = "Default Book Service";
        this.enableLogging = true;
    }

    // Constructor with BookRepository (for constructor injection)
    public BookService(BookRepository bookRepository) {
        System.out.println("BookService: Constructor with BookRepository called");
        this.bookRepository = bookRepository;
        this.serviceName = "Constructor Injected Book Service";
        this.enableLogging = true;
        System.out.println("BookService: BookRepository injected via constructor");
    }

    // Constructor with BookRepository and serviceName (for constructor injection with multiple params)
    public BookService(BookRepository bookRepository, String serviceName) {
        System.out.println("BookService: Constructor with BookRepository and serviceName called");
        this.bookRepository = bookRepository;
        this.serviceName = serviceName;
        this.enableLogging = true;
        System.out.println("BookService: BookRepository and serviceName injected via constructor");
    }

    // Constructor with all parameters
    public BookService(BookRepository bookRepository, String serviceName, boolean enableLogging) {
        System.out.println("BookService: Full constructor called");
        this.bookRepository = bookRepository;
        this.serviceName = serviceName;
        this.enableLogging = enableLogging;
        System.out.println("BookService: All dependencies injected via constructor");
    }

    // Setter method for BookRepository (for setter injection)
    public void setBookRepository(BookRepository bookRepository) {
        System.out.println("BookService: BookRepository setter called");
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository injected via setter");
    }

    // Setter method for serviceName (for setter injection)
    public void setServiceName(String serviceName) {
        System.out.println("BookService: ServiceName setter called");
        this.serviceName = serviceName;
        System.out.println("BookService: ServiceName set to: " + serviceName);
    }

    // Setter method for enableLogging (for setter injection)
    public void setEnableLogging(boolean enableLogging) {
        System.out.println("BookService: EnableLogging setter called");
        this.enableLogging = enableLogging;
        System.out.println("BookService: EnableLogging set to: " + enableLogging);
    }

    // Getter methods
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isEnableLogging() {
        return enableLogging;
    }

    // Business methods
    public Book addBook(Book book) {
        if (enableLogging) {
            System.out.println("BookService (" + serviceName + "): Adding book - " + book.getTitle());
        }
        return bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        if (enableLogging) {
            System.out.println("BookService (" + serviceName + "): Finding book by ID - " + id);
        }
        return bookRepository.findById(id);
    }

    public List<Book> findAllBooks() {
        if (enableLogging) {
            System.out.println("BookService (" + serviceName + "): Retrieving all books");
        }
        return bookRepository.findAll();
    }

    public List<Book> findBooksByTitle(String title) {
        if (enableLogging) {
            System.out.println("BookService (" + serviceName + "): Searching books by title - " + title);
        }
        return bookRepository.findByTitle(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        if (enableLogging) {
            System.out.println("BookService (" + serviceName + "): Searching books by author - " + author);
        }
        return bookRepository.findByAuthor(author);
    }

    public Book updateBook(Book book) {
        if (enableLogging) {
            System.out.println("BookService (" + serviceName + "): Updating book - " + book.getTitle());
        }
        if (book.getId() != null && bookRepository.existsById(book.getId())) {
            return bookRepository.save(book);
        } else {
            System.out.println("BookService: Book not found for update");
            return null;
        }
    }

    public void deleteBook(Long id) {
        if (enableLogging) {
            System.out.println("BookService (" + serviceName + "): Deleting book with ID - " + id);
        }
        bookRepository.deleteById(id);
    }

    public long getTotalBooksCount() {
        if (enableLogging) {
            System.out.println("BookService (" + serviceName + "): Getting total books count");
        }
        return bookRepository.count();
    }

    public void displayServiceInfo() {
        System.out.println("\n=== BookService Information ===");
        System.out.println("Service Name: " + serviceName);
        System.out.println("Enable Logging: " + enableLogging);
        System.out.println("Repository injected: " + (bookRepository != null ? "Yes" : "No"));
        if (bookRepository != null) {
            System.out.println("Total books in repository: " + bookRepository.count());
        }
        System.out.println("================================\n");
    }
}
