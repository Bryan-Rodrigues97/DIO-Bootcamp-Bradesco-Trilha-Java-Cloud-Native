package com.brdev.dio.model;

import java.math.BigDecimal;

public non-sealed class BankAccountCA extends BankAccount {

    private BigDecimal limit = BigDecimal.valueOf(100);

    protected BankAccountCA(int number, BigDecimal balance, Customer customer) {
        super(number, balance, customer);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.add(limit);
    }

    @Override
    public boolean withdrawal(BigDecimal amount) {
        if (balance.add(limit).compareTo(amount) > 0) {
            this.setBalance(balance.subtract(amount));
            return true;
        }
        return false;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Conta Corrente{" +
                "número='" + number + '\'' +
                ", saldo='" + balance + '\'' +
                ", limite='" + limit + '\'' +
                ", cliente='" + customer + '\'' +
                '}';
    }

}
