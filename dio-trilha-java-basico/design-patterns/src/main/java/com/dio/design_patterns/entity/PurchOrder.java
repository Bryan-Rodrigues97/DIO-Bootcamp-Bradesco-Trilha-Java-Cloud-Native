package com.dio.design_patterns.entity;

import com.dio.design_patterns.entity.enums.WorkflowStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_purch_order")
public class PurchOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "purch_amount")
    private BigDecimal purchAmount;

    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private WorkflowStatus workflowStatus = WorkflowStatus.DRAFT;

    public PurchOrder(Long id, String description, BigDecimal purchAmount, LocalDateTime createdDateTime, WorkflowStatus workflowStatus) {
        this.id = id;
        this.description = description;
        this.purchAmount = purchAmount;
        this.createdDateTime = createdDateTime;
        this.workflowStatus = workflowStatus;
    }

    public PurchOrder(String description, BigDecimal purchAmount) {
        this.description = description;
        this.purchAmount = purchAmount;
    }

    public PurchOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPurchAmount() {
        return purchAmount;
    }

    public void setPurchAmount(BigDecimal purchAmount) {
        this.purchAmount = purchAmount;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public WorkflowStatus getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(WorkflowStatus workflowStatus) {
        this.workflowStatus = workflowStatus;
    }
}
