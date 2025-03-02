package com.brdev.dio;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContaTerminal contaTerminal = new ContaTerminal();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);

        System.out.println("Por favor, digite seu nome: ");
        contaTerminal.setNomeCliente(scanner.nextLine());

        System.out.println("Digite sua agência: ");
        contaTerminal.setAgencia(scanner.nextLine());

        System.out.println("Digite o número da sua conta: ");
        contaTerminal.setNumero(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Por favor, digite seu saldo: ");
        contaTerminal.setSaldo(scanner.nextDouble());
        scanner.nextLine();
        scanner.close();

        System.out.printf("Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %d e seu saldo %.2f já está disponível para saque",
                contaTerminal.getNomeCliente(),
                contaTerminal.getAgencia(),
                contaTerminal.getNumero(),
                contaTerminal.getSaldo());

    }
}
