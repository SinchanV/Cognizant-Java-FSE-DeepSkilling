-- Insert sample data for books table
-- This file will be automatically executed by Spring Boot if present

INSERT INTO books (title, author, isbn, category, price, publisher, description, available, total_copies, available_copies, created_at, updated_at) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', '978-0-7432-7356-5', 'Fiction', 12.99, 'Scribner', 'A classic American novel', true, 5, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('To Kill a Mockingbird', 'Harper Lee', '978-0-06-112008-4', 'Fiction', 14.99, 'J. B. Lippincott & Co.', 'A novel about serious issues of rape and racial inequality', true, 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('1984', 'George Orwell', '978-0-452-28423-4', 'Dystopian', 13.99, 'Secker & Warburg', 'A dystopian social science fiction novel', true, 4, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Spring in Action', 'Craig Walls', '978-1-935182-35-1', 'Technology', 49.99, 'Manning Publications', 'Comprehensive guide to Spring Framework', true, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Effective Java', 'Joshua Bloch', '978-0-321-35668-0', 'Technology', 45.99, 'Addison-Wesley', 'Best practices for Java programming language', true, 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Clean Code', 'Robert C. Martin', '978-0-13-235088-4', 'Technology', 42.99, 'Prentice Hall', 'A handbook of agile software craftsmanship', true, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Design Patterns', 'Gang of Four', '978-0-201-63361-0', 'Technology', 54.99, 'Addison-Wesley', 'Elements of reusable object-oriented software', true, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
