package com.uncodigo.accounts.interfaces;

import com.uncodigo.accounts.models.BankAccount;
import com.uncodigo.users.models.User;

import java.util.Currency;
import java.util.List;

public interface IBankAccountService {
    List<BankAccount> getAllAccounts();
    BankAccount createAccount(String accountNumber, User accountHolder, Currency currency, double initialBalance);
    BankAccount getAccount(String accountNumber);
    boolean deposit(String accountNumber, double amount);
    boolean withdraw(String accountNumber, double amount);
    boolean transfer(String fromAccountNumber, String toAccountNumber, double amount);
}
