package com.junit.order;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * OrderedTests class demonstrating JUnit 5 test execution order
 * This class shows how to control test execution order using @TestMethodOrder and @Order
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("User Account Integration Tests - Ordered Execution")
public class OrderedTests {
    
    private static UserAccount userAccount;
    private static UserAccount secondAccount;
    
    @BeforeAll
    static void setupClass() {
        System.out.println("=== Starting Ordered Tests ===");
        userAccount = new UserAccount();
        secondAccount = new UserAccount();
    }
    
    @AfterAll
    static void tearDownClass() {
        System.out.println("=== Finished Ordered Tests ===");
        System.out.println("Final Transaction History:");
        userAccount.getTransactionHistory().forEach(System.out::println);
    }
    
    @BeforeEach
    void setUp() {
        System.out.println("Preparing for test...");
    }
    
    @AfterEach
    void tearDown() {
        System.out.println("Test completed. Current balance: $" + userAccount.getBalance());
        System.out.println("---");
    }
    
    @Test
    @Order(1)
    @DisplayName("1. Create User Account")
    void testCreateAccount() {
        System.out.println("TEST 1: Creating user account");
        
        // Test account creation
        assertDoesNotThrow(() -> {
            userAccount.createAccount("john_doe", "john@example.com");
        });
        
        assertEquals("john_doe", userAccount.getUsername());
        assertEquals("john@example.com", userAccount.getEmail());
        assertFalse(userAccount.isActive());
        assertEquals(0.0, userAccount.getBalance());
        assertEquals(1, userAccount.getTransactionCount());
        
        System.out.println("✓ Account created successfully");
    }
    
    @Test
    @Order(2)
    @DisplayName("2. Activate User Account")
    void testActivateAccount() {
        System.out.println("TEST 2: Activating user account");
        
        // Account should be created first (from previous test)
        assertNotNull(userAccount.getUsername());
        
        // Test account activation
        assertDoesNotThrow(() -> {
            userAccount.activateAccount();
        });
        
        assertTrue(userAccount.isActive());
        assertEquals(2, userAccount.getTransactionCount());
        
        System.out.println("✓ Account activated successfully");
    }
    
    @Test
    @Order(3)
    @DisplayName("3. Make Initial Deposit")
    void testInitialDeposit() {
        System.out.println("TEST 3: Making initial deposit");
        
        // Account should be activated first
        assertTrue(userAccount.isActive());
        
        // Test initial deposit
        assertDoesNotThrow(() -> {
            userAccount.deposit(1000.0);
        });
        
        assertEquals(1000.0, userAccount.getBalance(), 0.001);
        assertEquals(3, userAccount.getTransactionCount());
        
        System.out.println("✓ Initial deposit of $1000 completed");
    }
    
    @Test
    @Order(4)
    @DisplayName("4. Make Additional Deposit")
    void testAdditionalDeposit() {
        System.out.println("TEST 4: Making additional deposit");
        
        // Should have initial balance from previous test
        assertEquals(1000.0, userAccount.getBalance(), 0.001);
        
        // Test additional deposit
        assertDoesNotThrow(() -> {
            userAccount.deposit(500.0);
        });
        
        assertEquals(1500.0, userAccount.getBalance(), 0.001);
        assertEquals(4, userAccount.getTransactionCount());
        
        System.out.println("✓ Additional deposit of $500 completed");
    }
    
    @Test
    @Order(5)
    @DisplayName("5. Make Withdrawal")
    void testWithdrawal() {
        System.out.println("TEST 5: Making withdrawal");
        
        // Should have balance from previous deposits
        assertEquals(1500.0, userAccount.getBalance(), 0.001);
        
        // Test withdrawal
        assertDoesNotThrow(() -> {
            userAccount.withdraw(300.0);
        });
        
        assertEquals(1200.0, userAccount.getBalance(), 0.001);
        assertEquals(5, userAccount.getTransactionCount());
        
        System.out.println("✓ Withdrawal of $300 completed");
    }
    
    @Test
    @Order(6)
    @DisplayName("6. Setup Second Account for Transfer")
    void testSetupSecondAccount() {
        System.out.println("TEST 6: Setting up second account for transfer");
        
        // Create and activate second account
        assertDoesNotThrow(() -> {
            secondAccount.createAccount("jane_doe", "jane@example.com");
            secondAccount.activateAccount();
        });
        
        assertTrue(secondAccount.isActive());
        assertEquals(0.0, secondAccount.getBalance(), 0.001);
        
        System.out.println("✓ Second account created and activated");
    }
    
    @Test
    @Order(7)
    @DisplayName("7. Transfer Money Between Accounts")
    void testTransferMoney() {
        System.out.println("TEST 7: Transferring money between accounts");
        
        // Both accounts should be ready
        assertTrue(userAccount.isActive());
        assertTrue(secondAccount.isActive());
        assertEquals(1200.0, userAccount.getBalance(), 0.001);
        assertEquals(0.0, secondAccount.getBalance(), 0.001);
        
        // Test transfer
        assertDoesNotThrow(() -> {
            userAccount.transfer(secondAccount, 200.0);
        });
        
        assertEquals(1000.0, userAccount.getBalance(), 0.001);
        assertEquals(200.0, secondAccount.getBalance(), 0.001);
        
        System.out.println("✓ Transfer of $200 completed");
    }
    
    @Test
    @Order(8)
    @DisplayName("8. Verify Transaction History")
    void testTransactionHistory() {
        System.out.println("TEST 8: Verifying transaction history");
        
        // Check transaction history
        List<String> history = userAccount.getTransactionHistory();
        assertFalse(history.isEmpty());
        assertTrue(history.size() >= 7); // Should have multiple transactions
        
        // Verify specific transactions exist
        assertTrue(history.stream().anyMatch(t -> t.contains("Account created")));
        assertTrue(history.stream().anyMatch(t -> t.contains("Account activated")));
        assertTrue(history.stream().anyMatch(t -> t.contains("Deposited")));
        assertTrue(history.stream().anyMatch(t -> t.contains("Withdrew")));
        assertTrue(history.stream().anyMatch(t -> t.contains("Transferred")));
        
        System.out.println("✓ Transaction history verified (" + history.size() + " transactions)");
    }
    
    @Test
    @Order(9)
    @DisplayName("9. Test Error Conditions")
    void testErrorConditions() {
        System.out.println("TEST 9: Testing error conditions");
        
        // Test insufficient funds
        assertThrows(IllegalStateException.class, () -> {
            userAccount.withdraw(2000.0); // More than available balance
        });
        
        // Test invalid deposit
        assertThrows(IllegalArgumentException.class, () -> {
            userAccount.deposit(-100.0); // Negative amount
        });
        
        // Test invalid withdrawal
        assertThrows(IllegalArgumentException.class, () -> {
            userAccount.withdraw(0.0); // Zero amount
        });
        
        System.out.println("✓ Error conditions tested successfully");
    }
    
    @Test
    @Order(10)
    @DisplayName("10. Final Balance Verification")
    void testFinalBalance() {
        System.out.println("TEST 10: Final balance verification");
        
        // Verify final balances
        assertEquals(1000.0, userAccount.getBalance(), 0.001);
        assertEquals(200.0, secondAccount.getBalance(), 0.001);
        
        // Verify both accounts are still active
        assertTrue(userAccount.isActive());
        assertTrue(secondAccount.isActive());
        
        System.out.println("✓ Final balance verification completed");
        System.out.println("  Main account balance: $" + userAccount.getBalance());
        System.out.println("  Second account balance: $" + secondAccount.getBalance());
    }
}
