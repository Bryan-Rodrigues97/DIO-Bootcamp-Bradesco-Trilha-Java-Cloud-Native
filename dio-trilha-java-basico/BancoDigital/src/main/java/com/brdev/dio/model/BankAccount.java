package com.brdev.dio.model;

import com.brdev.dio.validator.InitialBalanceValidator;

import java.math.BigDecimal;
import java.util.Objects;

public abstract sealed class BankAccount implements InitialBalanceValidator permits BankAccountCA, BankAccountSA
         {
    protected int number;
    protected BigDecimal balance;
    protected Customer customer;

    public static BankAccount construct(BankAccountType type, int number, BigDecimal balance, Customer customer) {
        switch (type)
        {
            case CHECKING_ACCOUNT -> {
                return new BankAccountCA(number, balance, customer);
            }
            case SAVING_ACCOUNT -> {
                return new BankAccountSA(number, balance, customer);
            }
        }

        throw new RuntimeException("Invalid account type");
    }

    protected BankAccount(int number, BigDecimal balance, Customer customer) {
        this.number = number;
        this.balance = balance;
        this.customer = customer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void deposit(BigDecimal amount) {
        this.setBalance(balance.add(amount));
    }

    public boolean withdrawal(BigDecimal amount) {
        if (balance.compareTo(amount) > 0) {
            this.setBalance(balance.subtract(amount));
            return true;
        }
        return false;
    }

    public boolean transfer(BigDecimal amount, BankAccount receiver) {
        if (withdrawal(amount)) {
            receiver.deposit(amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean validateInitialBalance() {
        return balance.compareTo(BigDecimal.ZERO) > 0;
    }

             @Override
    public String toString() {
        return "BankAccount{" +
                "number='" + number + '\'' +
                ", balance='" + balance + '\'' +
                ", customer='" + customer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return number == that.number || Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, customer);
    }
}