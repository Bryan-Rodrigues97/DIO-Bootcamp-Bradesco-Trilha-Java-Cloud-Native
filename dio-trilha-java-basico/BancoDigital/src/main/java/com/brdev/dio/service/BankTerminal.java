package com.brdev.dio.service;

import com.brdev.dio.model.*;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class BankTerminal {
    private final Bank bank;
    private Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);

    public BankTerminal(Bank bank) {
        this.bank = bank;
    }

    public void operate() {
        int option = 0;
        while (option != -1) {
            showMainMenu();

            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println();
                    System.out.println("========== Contas ==========");
                    bank.showAccounts();
                }
                case 2 -> {
                    BankAccount a = createAccount();
                    if (a != null) {
                        if (!a.getCustomer().validateDocument()) {
                            System.out.println("O documento do cliente não é válido");
                            continue;
                        }
                        if (!a.validateInitialBalance()) {
                            System.out.println("A conta cadastrada deve possuir um saldo inicial positivo");
                            continue;
                        }
                        if (bank.exists(a)) {
                            System.out.println("O número da conta e/ou CNPJ/CPF do Cliente já está cadastrado");
                            continue;
                        }
                        bank.add(a);
                    }
                }
                case 3 -> {
                    selectAccount(bank);
                }
                case -1 -> {
                    System.out.println("Obrigado por ser nosso cliente!");
                }
                default -> {
                    System.out.println("Opção inválida");
                }
            }
        }
        scanner.close();
    }

    private void showMainMenu() {
        System.out.println();
        System.out.printf("========== %s ==========\n", bank.getName());
        System.out.println("[1] - Listar todas as contas");
        System.out.println("[2] - Adicionar conta");
        System.out.println("[3] - Selecionar conta");
        System.out.println("[-1] - Sair");
        System.out.println();
        System.out.print("Opção: ");
    }

    private BankAccount createAccount() {
        int option = 0;

        while (option != -1) {
            System.out.println();
            System.out.println("========== Nova Conta ==========");
            System.out.println("[1] - Conta Corrente");
            System.out.println("[2] - Conta Poupança");
            System.out.println("[-1] - Sair");
            System.out.println();
            System.out.print("Opção: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    return createBankAccount(BankAccountType.CHECKING_ACCOUNT);
                }
                case 2 -> {
                    return createBankAccount(BankAccountType.SAVING_ACCOUNT);
                }
                case -1 -> {
                    break;
                }
                default -> {
                    System.out.println("Opção Inválida");
                }
            }
        }

        return null;
    }

    private BankAccount createBankAccount(BankAccountType type) {
        Customer c = createCustomer();

        System.out.print("Número da conta: ");
        int accountNum = scanner.nextInt();

        System.out.print("Saldo incial: ");
        BigDecimal balance = BigDecimal.valueOf(scanner.nextDouble());

        return BankAccount.construct(type,accountNum,balance, c);
    }

    private Customer createCustomer() {
        System.out.print("Nome do Cliente: ");
        String name = scanner.nextLine();

        System.out.print("CNPJ/CPF do Cliente: ");
        String cnpjCpf = scanner.nextLine();

        return new Customer(name, cnpjCpf);
    }

    private void accountOperations(BankAccount account, Bank bank) {
        int option = 0;

        while (option != -1) {
            System.out.printf("Conta selecionada: %d. Cliente %s\n", account.getNumber(), account.getCustomer().getName());
            System.out.println("[1] - Realizar saque");
            System.out.println("[2] - Realizar depósito");
            System.out.println("[3] - Realizar transferência");
            System.out.println("[4] - Exibir saldo");
            if (account instanceof BankAccountSA) {
                System.out.println("[5] - Aplicar rendimento");
            }
            System.out.println("[-1] - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.print("Valor do saque: ");
                    BigDecimal amount = BigDecimal.valueOf(scanner.nextDouble());
                    if(!account.withdrawal(amount)) {
                        System.out.println("Saldo insuficiente para realizar a operação");
                    }
                }
                case 2 -> {
                    System.out.print("Valor do deposito: ");
                    BigDecimal amount = BigDecimal.valueOf(scanner.nextDouble());
                    account.deposit(amount);
                }
                case 3 -> {
                    System.out.print("Número da conta destino: ");
                    BankAccount receiver = bank.find(scanner.nextInt());
                    if (receiver == null) {
                        System.out.println("Conta destino não localizada");
                        continue;
                    }
                    System.out.print("Valor da transferência: ");
                    BigDecimal amount = BigDecimal.valueOf(scanner.nextDouble());
                    if(!account.transfer(amount, receiver)) {
                        System.out.println("Saldo insuficiente para realizar a operação");
                    }
                }
                case 4 -> {
                    System.out.printf("Saldo atual: R$ %.2f\n", account.getBalance());
                }
                case 5 -> {
                    if (! (account instanceof BankAccountSA)) {
                        System.out.println("Opção Inválida");
                        continue;
                    }
                    System.out.println("Aplicando rendimentos");
                    ((BankAccountSA) account).applyRevenue(10.0);
                }
                case -1 -> {
                    break;
                }
                default -> {
                    System.out.println("Opção Inválida");
                }
            }
        }
    }

    private void selectAccount(Bank bank) {
        int option = 0;

        while (option != -1) {
            System.out.println();
            System.out.println("========== Operações de Conta ==========");
            System.out.println("[1] - Pesquisar");
            System.out.println("[-1] - Sair");
            System.out.println();
            System.out.print("Opção: ");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.print("Número da Conta: ");
                    BankAccount bankAccount = bank.find(scanner.nextInt());
                    if (bankAccount == null) {
                        System.out.print("Conta não localizada");
                        continue;
                    }
                    accountOperations(bankAccount, bank);
                }
                case -1 -> {
                    break;
                }
                default -> {
                    System.out.println("Opção Inválida");
                }
            }
        }
    }
}
