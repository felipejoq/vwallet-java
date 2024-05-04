package com.uncodigo.accounts.models.transactions;

import com.uncodigo.accounts.enums.TransactionType;
import com.uncodigo.accounts.models.BankAccount;

public class Deposit extends Transaction {
    public Deposit(BankAccount fromAccount, double amount) {
        super(fromAccount, TransactionType.DEPOSIT, amount);
    }
}
