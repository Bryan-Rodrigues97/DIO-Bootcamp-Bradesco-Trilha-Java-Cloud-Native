# Fluxo de Aprovação de Ordens de Compra - Design Patterns

Este projeto foi desenvolvido com o objetivo de aplicar padrões de projeto (Design Patterns) em um fluxo de trabalho para aprovação de ordens de compra.

## Tecnologias Utilizadas
- **Java**
- **Spring Boot**
- **Spring Security** (Autenticação simples)
- **H2**

## Padrões de Projeto Implementados

### 1. Chain of Responsibility
Utilizado para estruturar a cadeia de validação da aprovação de uma ordem de compra. Cada etapa da aprovação é tratada por um manipulador específico que pode aprovar a ordem ou encaminhá-la para o próximo manipulador.

### 2. Facade
Implementado para simplificar o acesso ao fluxo de aprovação, encapsulando a complexidade do uso do Chain of Responsibility e garantindo um ponto central de acesso ao processo.

## Funcionalidades
- **Autenticação**: Endpoints protegidos com autenticação simples via Spring Security.
- **Fluxo de Aprovação**: Implementação de um fluxo estruturado com padrões de projeto.
- **Criação de Usuários Padrão**: Inicialização de usuários via `CommandLineRunner`.
- **Criação de Ordens de Compra Padrão**: Inicialização de OC's via `CommandLineRunner`.