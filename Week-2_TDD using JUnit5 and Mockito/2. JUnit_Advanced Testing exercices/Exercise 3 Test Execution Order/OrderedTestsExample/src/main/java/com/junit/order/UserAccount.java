package com.junit.order;

import java.util.ArrayList;
import java.util.List;

/**
 * UserAccount class for demonstrating ordered tests
 * Represents a user account with operations that might need to be tested in sequence
 */
public class UserAccount {
    private String username;
    private String email;
    private double balance;
    private boolean isActive;
    private List<String> transactionHistory;
    
    public UserAccount() {
        this.transactionHistory = new ArrayList<>();
        this.balance = 0.0;
        this.isActive = false;
    }
    
    public UserAccount(String username, String email) {
        this();
        this.username = username;
        this.email = email;
    }
    
    // Account creation
    public void createAccount(String username, String email) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.username = username;
        this.email = email;
        this.transactionHistory.add("Account created for: " + username);
    }
    
    // Account activation
    public void activateAccount() {
        if (username == null) {
            throw new IllegalStateException("Account must be created before activation");
        }
        this.isActive = true;
        this.transactionHistory.add("Account activated for: " + username);
    }
    
    // Deposit money
    public void deposit(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account must be activated before deposit");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
        this.transactionHistory.add("Deposited: $" + amount + ", Balance: $" + balance);
    }
    
    // Withdraw money
    public void withdraw(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account must be activated before withdrawal");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        this.balance -= amount;
        this.transactionHistory.add("Withdrew: $" + amount + ", Balance: $" + balance);
    }
    
    // Transfer money to another account
    public void transfer(UserAccount toAccount, double amount) {
        if (!isActive || !toAccount.isActive) {
            throw new IllegalStateException("Both accounts must be activated for transfer");
        }
        this.withdraw(amount); // This will handle validation
        toAccount.deposit(amount);
        this.transactionHistory.add("Transferred: $" + amount + " to " + toAccount.getUsername());
    }
    
    // Account deactivation
    public void deactivateAccount() {
        if (balance > 0) {
            throw new IllegalStateException("Account must have zero balance before deactivation");
        }
        this.isActive = false;
        this.transactionHistory.add("Account deactivated for: " + username);
    }
    
    // Getters
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
    
    public int getTransactionCount() {
        return transactionHistory.size();
    }
}
