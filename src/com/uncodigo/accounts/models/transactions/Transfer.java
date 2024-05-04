package com.uncodigo.accounts.models.transactions;

import com.uncodigo.accounts.enums.TransactionType;
import com.uncodigo.accounts.models.BankAccount;

public class Transfer extends Transaction {

    private BankAccount toAccount;

    public Transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        super(fromAccount, TransactionType.TRANSFER, amount);
        this.toAccount = toAccount;
    }

    public BankAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(BankAccount toAccount) {
        this.toAccount = toAccount;
    }
}
