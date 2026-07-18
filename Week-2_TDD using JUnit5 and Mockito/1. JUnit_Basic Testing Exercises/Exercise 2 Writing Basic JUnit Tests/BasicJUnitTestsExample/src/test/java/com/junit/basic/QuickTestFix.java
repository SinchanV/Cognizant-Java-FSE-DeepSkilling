package com.junit.basic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Quick test to verify the fix for capitalizeWords method
 */
public class QuickTestFix {
    
    @Test
    public void testCapitalizeWordsFix() {
        StringUtils stringUtils = new StringUtils();
        
        // Test the specific case that was failing
        String result = stringUtils.capitalizeWords("  hello   world  ");
        System.out.println("Input: '  hello   world  '");
        System.out.println("Output: '" + result + "'");
        System.out.println("Expected: 'Hello World'");
        
        assertEquals("Hello World", result);
        
        // Test other cases to make sure we didn't break anything
        assertEquals("Hello", stringUtils.capitalizeWords("hello"));
        assertEquals("Hello World", stringUtils.capitalizeWords("hello world"));
        assertEquals("", stringUtils.capitalizeWords(""));
        assertEquals("", stringUtils.capitalizeWords("   "));
        assertNull(stringUtils.capitalizeWords(null));
        
        System.out.println("All tests passed!");
    }
}
