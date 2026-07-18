package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Book entity using Spring Data JPA
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Find books by title (case-insensitive, partial match)
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    // Find books by author (case-insensitive, partial match)
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    // Find books by category
    List<Book> findByCategoryIgnoreCase(String category);
    
    // Find book by ISBN
    Optional<Book> findByIsbn(String isbn);
    
    // Find available books
    List<Book> findByAvailableTrue();
    
    // Find books by price range
    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);
    
    // Find books by author and category
    List<Book> findByAuthorContainingIgnoreCaseAndCategoryIgnoreCase(String author, String category);
    
    // Custom query to find books with available copies
    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findBooksWithAvailableCopies();
    
    // Custom query to find books by publisher
    @Query("SELECT b FROM Book b WHERE b.publisher LIKE %:publisher%")
    List<Book> findByPublisherContaining(@Param("publisher") String publisher);
    
    // Custom query to count books by category
    @Query("SELECT COUNT(b) FROM Book b WHERE b.category = :category")
    Long countBooksByCategory(@Param("category") String category);
    
    // Custom query to find most expensive books
    @Query("SELECT b FROM Book b ORDER BY b.price DESC")
    List<Book> findBooksOrderByPriceDesc();
    
    // Find books by multiple categories
    List<Book> findByCategoryIn(List<String> categories);
    
    // Check if book exists by ISBN
    boolean existsByIsbn(String isbn);
    
    // Count available books
    @Query("SELECT COUNT(b) FROM Book b WHERE b.available = true")
    Long countAvailableBooks();
}
