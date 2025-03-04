package com.brdev.dio.model;

import com.brdev.dio.validator.CNPJCPFValidator;
import com.brdev.dio.validator.DocumentValidator;

import java.util.Objects;

public class Customer implements DocumentValidator {
    private String name;
    private String cnpjCpf;

    public Customer(String name, String cnpjCpf) {
        this.name = name;
        this.cnpjCpf = cnpjCpf.replaceAll("[^0-9]", "");
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    @Override
    public boolean validateDocument() {
        CNPJCPFValidator cnpjcpfValidator = new CNPJCPFValidator(cnpjCpf);
        return cnpjcpfValidator.validate();
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + name + '\'' +
                ", CNPJ/CPF='" + cnpjCpf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cnpjCpf, customer.cnpjCpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cnpjCpf);
    }
}
