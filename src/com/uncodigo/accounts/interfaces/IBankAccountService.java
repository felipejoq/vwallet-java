package com.uncodigo.accounts.interfaces;

import com.uncodigo.accounts.models.BankAccount;
import com.uncodigo.accounts.models.transactions.Transaction;
import com.uncodigo.users.models.User;

import java.util.Currency;
import java.util.List;

/**
 * Interface for Bank Account Service
 */
public interface IBankAccountService {

    /**
     * Get all bank accounts
     *
     * @return List of BankAccount
     */
    List<BankAccount> getAllAccounts();

    /**
     * Create a new bank account
     *
     * @param accountNumber  Account number
     * @param accountHolder  Account holder
     * @param currency       Currency of the account
     * @param initialBalance Initial balance default 0.0
     * @return BankAccount created
     */
    BankAccount createAccount(String accountNumber, User accountHolder, Currency currency, double initialBalance);

    /**
     * Get a bank account by account number
     *
     * @param accountNumber Account number to get
     * @return BankAccount found
     */
    BankAccount getAccount(String accountNumber);

    /**
     * Deposit money to a bank account
     *
     * @param accountNumber Account number to deposit
     * @param amount        Amount to deposit to the account
     * @return boolean if the deposit was successful, false otherwise
     */
    boolean deposit(String accountNumber, double amount);

    /**
     * Withdraw money from a bank account
     *
     * @param accountNumber Account number to withdraw from
     * @param amount        Amount to withdraw
     * @return boolean if the withdrawal was successful, false otherwise
     */
    boolean withdraw(String accountNumber, double amount);

    /**
     * transfer money from one account to another
     *
     * @param fromAccountNumber Account number to transfer from
     * @param toAccountNumber   Account number to transfer to
     * @param amount            Amount to transfer
     * @return boolean if the transfer was successful, false otherwise
     */
    boolean transfer(String fromAccountNumber, String toAccountNumber, double amount);
}
