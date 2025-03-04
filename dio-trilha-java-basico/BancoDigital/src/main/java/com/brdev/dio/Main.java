package com.brdev.dio;

import com.brdev.dio.model.Bank;
import com.brdev.dio.service.BankTerminal;

public class Main {
    public static void main(String[] args) {
        BankTerminal bankTerminal = new BankTerminal(new Bank("DIO Bank"));
        bankTerminal.operate();
    }
}