package com.brdev.dio.model;

import com.brdev.dio.util.Revenue;

import java.math.BigDecimal;

public non-sealed class BankAccountSA extends BankAccount implements Revenue {

    protected BankAccountSA(int number, BigDecimal balance, Customer customer) {
        super(number, balance, customer);
    }

    @Override
    public void applyRevenue(Double percent) {
        this.setBalance(balance.add(balance.multiply(BigDecimal.valueOf(percent/100))));
    }

    @Override
    public String toString() {
        return "Conta poupança{" +
                "número='" + number + '\'' +
                ", saldo='" + balance + '\'' +
                ", cliente='" + customer + '\'' +
                '}';
    }
}
