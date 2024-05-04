package com.uncodigo.accounts.models.transactions;

import com.uncodigo.accounts.enums.TransactionType;
import com.uncodigo.accounts.models.BankAccount;

public class Withdraw extends Transaction {
    public Withdraw(BankAccount fromAccount, double amount) {
        super(fromAccount, TransactionType.WITHDRAW, amount);
    }
}
