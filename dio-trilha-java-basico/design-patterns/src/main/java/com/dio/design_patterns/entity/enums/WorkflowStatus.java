package com.dio.design_patterns.entity.enums;

public enum WorkflowStatus {
    REJECTED("Rejeitado", null),
    CANCELED("Cancelado", null),
    APPROVED("Aprovado", null),
    DIRECTOR_APPROVAL("Aprovação Diretoria", APPROVED),
    MANAGER_APPROVAL("Aprovação Gerencia", DIRECTOR_APPROVAL),
    ANALIST_APPROVAL("Aprovação Analista", MANAGER_APPROVAL),
    SENDED("Enviado", ANALIST_APPROVAL),
    DRAFT("Rascunho", SENDED);

    private final String description;
    private final WorkflowStatus next;

    WorkflowStatus(String description, WorkflowStatus next) {
        this.description = description;
        this.next = next;
    }

    public String getDescription() {
        return description;
    }

    public WorkflowStatus getNext() {
        return next;
    }
}
