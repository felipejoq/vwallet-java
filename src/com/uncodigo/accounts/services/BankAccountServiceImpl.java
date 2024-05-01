package com.uncodigo.accounts.services;

import com.uncodigo.accounts.interfaces.IBankAccountService;
import com.uncodigo.accounts.models.BankAccount;
import com.uncodigo.users.models.User;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class BankAccountServiceImpl implements IBankAccountService {

    private List<BankAccount> accounts;

    public BankAccountServiceImpl() {
        this.accounts = new ArrayList<>();
    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return this.accounts;
    }

    @Override
    public BankAccount createAccount(String accountNumber, User accountHolder, Currency currency, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, accountHolder, currency, initialBalance);
        this.accounts.add(account);
        return account;
    }

    @Override
    public BankAccount getAccount(String accountNumber) {
        return this.accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        BankAccount account = this.getAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        BankAccount account = this.getAccount(accountNumber);
        if (account != null) {
            double balance = account.getBalance();
            if (balance >= amount) {
                account.setBalance(balance - amount);
                return true;
            } else {
                throw new IllegalArgumentException("Insufficient balance");
            }
        }
        return false;
    }

    @Override
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = this.getAccount(fromAccountNumber);
        BankAccount toAccount = this.getAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            double balance = fromAccount.getBalance();
            if (balance >= amount) {
                fromAccount.setBalance(balance - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                return true;
            } else {
                throw new IllegalArgumentException("Insufficient balance");
            }
        }
        return false;
    }
}
