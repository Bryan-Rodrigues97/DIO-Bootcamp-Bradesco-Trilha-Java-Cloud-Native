package com.brdev.dio.model;

import java.util.*;

public class Bank {
    private String name;
    private Set<BankAccount> accounts;

    public Bank(String name) {
        this.name = name;
        this.accounts = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public BankAccount find(int number) {
        BankAccount bankAccountFound = null;
        for (BankAccount bankAccount : accounts) {
            if (bankAccount.getNumber() == number) {
                bankAccountFound = bankAccount;
                break;
            }
        }
        return bankAccountFound;
    }

    public boolean exists(BankAccount account) {
        for (BankAccount bankAccount : accounts) {
            if (bankAccount.equals(account)) {
                return true;
            } else if (bankAccount.getCustomer().equals(account.customer)) {
                return true;
            }
        }
        return false;
    }

    public void add(BankAccount account) {
        accounts.add(account);
    }

    public void showAccounts() {
        accounts.forEach(System.out::println);
    }
}
