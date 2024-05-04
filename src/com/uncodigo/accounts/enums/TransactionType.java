package com.uncodigo.accounts.enums;

public enum TransactionType {
    /*
     * Enum que representa los tipos de transacciones que se pueden realizar en una cuenta bancaria.
     */
    DEPOSIT("Depósito"),
    WITHDRAW("Retiro"),
    TRANSFER("Transferencia");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
