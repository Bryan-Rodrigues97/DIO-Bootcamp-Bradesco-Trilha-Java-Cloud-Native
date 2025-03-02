package com.brdev.dio;

import java.util.Scanner;

public class Contador {
    public static void main(String[] args) {
        Scanner terminal = new Scanner(System.in);

        int parametroUm = getInt(terminal, "Digite o primeiro parâmetro: ");
        int parametroDois = getInt(terminal, "Digite o segundo parâmetro: ");

        terminal.close();

        try {
            contar(parametroUm, parametroDois);

        }catch (ParametrosInvalidosException exception) {
            System.out.println(exception.getMessage());
        }

    }
    static int getInt(Scanner scanner, String message) {
        System.out.println(message);
        int n = scanner.nextInt();
        scanner.nextLine();
        return n;
    }
    static void contar(int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
        if (parametroUm >= parametroDois) {
            throw new ParametrosInvalidosException();
        }
        int contagem = parametroDois - parametroUm;

        for (int i=1; i<=contagem;i++) {
            System.out.printf("Imprimindo o número %d%n", i);
        }
    }
}