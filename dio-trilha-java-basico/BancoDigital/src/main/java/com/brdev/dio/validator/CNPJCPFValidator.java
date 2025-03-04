package com.brdev.dio.validator;

import java.util.Arrays;

public class CNPJCPFValidator {
    private String cnpjCpf;

    public CNPJCPFValidator(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf.replaceAll("[^0-9]", "");
    }

    public boolean validate() {
        switch (cnpjCpf.length()) {
            case 11 -> {
                return validateCPF();
            }
            case 14 -> {
                return validateCNPJ();
            }
            default -> {
                return false;
            }
        }
    }

    public boolean validateCPF() {
        return cnpjCpf.length() == 11;
    }

    public boolean validateCNPJ() {
        if (cnpjCpf.length() != 14) {
            return false;
        }

        int[] cnpjNums = Arrays
                .stream(cnpjCpf.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        int first = this.CNPJ_firstDigitCheckSum(cnpjNums);
        int second = this.CNPJ_secondDigitCheckSum(cnpjNums);

        return first == cnpjNums[12] && second == cnpjNums[13];
    }

    private int CNPJ_firstDigitCheckSum(int[] cnpjArr) {
        int[] digitList = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int checksum = 0, rest = 0;

        for (int i=0; i<12; i++) {
            checksum += (cnpjArr[i] * digitList[i]);
        }

        rest = checksum % 11;
        return rest < 2 ? 0 : 11-rest;
    }

    private int CNPJ_secondDigitCheckSum(int[] cnpjArr) {
        int[] digitList = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int checksum = 0, rest = 0;

        for (int i=0; i<13; i++) {
            checksum += (cnpjArr[i] * digitList[i]);
        }

        rest = checksum % 11;
        return rest < 2 ? 0 : 11-rest;
    }
}

