package com.library;

import com.library.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;

/**
 * JUnit test for Spring IoC Container configuration
 */
public class SpringIoCConfigurationTest {

    @Test
    public void testSpringContextLoads() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        assertNotNull("Spring context should load successfully", context);
    }

    @Test
    public void testBookServiceBeanCreation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        assertNotNull("BookService bean should be created", bookService);
    }

    @Test
    public void testDependencyInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        assertNotNull("BookService should have BookRepository injected", 
                     bookService.getBookRepository());
    }

    @Test
    public void testBookServiceFunctionality() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        
        long bookCount = bookService.getTotalBooksCount();
        assertTrue("BookService should return book count > 0", bookCount > 0);
    }
}
