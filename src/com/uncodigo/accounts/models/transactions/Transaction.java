package com.uncodigo.accounts.models.transactions;

import com.uncodigo.accounts.enums.TransactionType;
import com.uncodigo.accounts.models.BankAccount;
import com.uncodigo.strings.StringUtils;

import java.util.Date;

public class Transaction {
    /*
     * Model for generic transaction
     */
    private final String transactionId;
    private final BankAccount fromAccount;
    private final TransactionType type;
    private final Date timestamp;
    private final double amount;

    public Transaction(BankAccount fromAccount, TransactionType type, double amount) {
        this.transactionId = "TR_" + StringUtils.getRandomString(10);
        this.timestamp = new Date();
        this.fromAccount = fromAccount;
        this.type = type;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public BankAccount getFromAccount() {
        return fromAccount;
    }

    public TransactionType getType() {
        return type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }
}
