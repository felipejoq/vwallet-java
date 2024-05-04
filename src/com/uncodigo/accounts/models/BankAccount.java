package com.uncodigo.accounts.models;

import com.uncodigo.accounts.models.transactions.Transaction;
import com.uncodigo.users.models.User;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class BankAccount {
    private final String accountNumber;
    private final User accountHolder;
    private Currency currency;
    private double balance;
    private final List<Transaction> transactions;

    public BankAccount(String accountNumber, User accountHolder, Currency currency, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.currency = currency;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
