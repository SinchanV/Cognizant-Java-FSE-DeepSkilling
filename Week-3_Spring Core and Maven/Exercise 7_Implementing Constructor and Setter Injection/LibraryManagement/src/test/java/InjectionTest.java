package com.library;

import com.library.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;

/**
 * JUnit test for Constructor and Setter Injection configurations
 */
public class InjectionTest {

    @Test
    public void testConstructorInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookServiceConstructor", BookService.class);
        
        assertNotNull("BookService should be created", bookService);
        assertNotNull("BookRepository should be injected", bookService.getBookRepository());
        assertEquals("Service name should be set via constructor", 
                    "Constructor Injected Book Service", bookService.getServiceName());
        assertTrue("Logging should be enabled", bookService.isEnableLogging());
    }

    @Test
    public void testSetterInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookServiceSetter", BookService.class);
        
        assertNotNull("BookService should be created", bookService);
        assertNotNull("BookRepository should be injected via setter", bookService.getBookRepository());
        assertEquals("Service name should be set via setter", 
                    "Setter Injected Book Service", bookService.getServiceName());
        assertTrue("Logging should be enabled via setter", bookService.isEnableLogging());
    }

    @Test
    public void testMixedInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookServiceMixed", BookService.class);
        
        assertNotNull("BookService should be created", bookService);
        assertNotNull("BookRepository should be injected via constructor", bookService.getBookRepository());
        assertEquals("Service name should be set via setter", 
                    "Mixed Injection Book Service", bookService.getServiceName());
        assertFalse("Logging should be disabled via setter", bookService.isEnableLogging());
    }

    @Test
    public void testMultipleConstructorParametersInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookServiceConstructorMultiple", BookService.class);
        
        assertNotNull("BookService should be created", bookService);
        assertNotNull("BookRepository should be injected", bookService.getBookRepository());
        assertEquals("Service name should be set via constructor", 
                    "Advanced Constructor Injected Service", bookService.getServiceName());
        assertTrue("Logging should be enabled via constructor", bookService.isEnableLogging());
    }

    @Test
    public void testIndexBasedConstructorInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookServiceConstructorIndexed", BookService.class);
        
        assertNotNull("BookService should be created", bookService);
        assertNotNull("BookRepository should be injected", bookService.getBookRepository());
        assertEquals("Service name should be set via index-based constructor", 
                    "Index-based Constructor Service", bookService.getServiceName());
    }

    @Test
    public void testTypeBasedConstructorInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookServiceConstructorTyped", BookService.class);
        
        assertNotNull("BookService should be created", bookService);
        assertNotNull("BookRepository should be injected", bookService.getBookRepository());
        assertEquals("Service name should be set via type-based constructor", 
                    "Type-based Constructor Service", bookService.getServiceName());
    }

    @Test
    public void testAllServiceBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        // Test that all configured service beans can be created
        assertNotNull(context.getBean("bookServiceConstructor"));
        assertNotNull(context.getBean("bookServiceConstructorMultiple"));
        assertNotNull(context.getBean("bookServiceSetter"));
        assertNotNull(context.getBean("bookServiceMixed"));
        assertNotNull(context.getBean("bookServiceConstructorIndexed"));
        assertNotNull(context.getBean("bookServiceConstructorTyped"));
    }
}
