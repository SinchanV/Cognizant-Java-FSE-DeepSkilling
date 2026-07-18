package com.library.repository;

import com.library.model.Book;
import java.util.*;

/**
 * Repository class for managing book data access operations
 */
public class BookRepository {
    
    private Map<Long, Book> books;
    private Long nextId;

    // Default constructor
    public BookRepository() {
        this.books = new HashMap<>();
        this.nextId = 1L;
        System.out.println("BookRepository initialized");
        initializeData();
    }

    private void initializeData() {
        System.out.println("Initializing repository with sample data");
        
        // Sample books
        save(new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", 
                     "Fiction", 12.99));
        save(new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", 
                     "Fiction", 14.99));
        save(new Book("1984", "George Orwell", "978-0-452-28423-4", 
                     "Dystopian", 13.99));
        save(new Book("Spring in Action", "Craig Walls", "978-1-935182-35-1", 
                     "Technology", 49.99));
        
        System.out.println("Repository initialized with " + books.size() + " books");
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(nextId++);
        }
        books.put(book.getId(), book);
        System.out.println("Book saved: " + book.getTitle());
        return book;
    }

    public Book findById(Long id) {
        Book book = books.get(id);
        if (book != null) {
            System.out.println("Book found by ID " + id + ": " + book.getTitle());
        } else {
            System.out.println("Book not found with ID: " + id);
        }
        return book;
    }

    public List<Book> findAll() {
        List<Book> allBooks = new ArrayList<>(books.values());
        System.out.println("Retrieved all books, count: " + allBooks.size());
        return allBooks;
    }

    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        System.out.println("Found " + result.size() + " books matching title: " + title);
        return result;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        System.out.println("Found " + result.size() + " books by author: " + author);
        return result;
    }

    public void deleteById(Long id) {
        Book removedBook = books.remove(id);
        if (removedBook != null) {
            System.out.println("Book deleted: " + removedBook.getTitle());
        } else {
            System.out.println("Book not found for deletion with ID: " + id);
        }
    }

    public boolean existsById(Long id) {
        boolean exists = books.containsKey(id);
        System.out.println("Book with ID " + id + " exists: " + exists);
        return exists;
    }

    public long count() {
        long count = books.size();
        System.out.println("Total books count: " + count);
        return count;
    }
}
