package com.dio.design_patterns.service.approvals;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.entity.User;
import com.dio.design_patterns.entity.enums.WorkflowStatus;
import com.dio.design_patterns.service.approvals.interfaces.ApprovalHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Approval implements ApprovalHandler {
    private Approval next;
    protected static final Logger logger = LoggerFactory.getLogger(Approval.class);

    @Override
    public boolean approves(User user, PurchOrder purchOrder) {
        return purchOrder.getWorkflowStatus() != WorkflowStatus.DRAFT
                && purchOrder.getWorkflowStatus() != WorkflowStatus.APPROVED
                && purchOrder.getWorkflowStatus() != WorkflowStatus.CANCELED
                && purchOrder.getWorkflowStatus() != WorkflowStatus.REJECTED;
    }

    @Override
    public boolean send(PurchOrder purchOrder) {
        return purchOrder.getWorkflowStatus() == WorkflowStatus.DRAFT;
    }

    @Override
    public boolean draft(PurchOrder purchOrder) {
        return purchOrder.getWorkflowStatus() == WorkflowStatus.CANCELED
            || purchOrder.getWorkflowStatus() == WorkflowStatus.REJECTED;
    }

    @Override
    public boolean cancel(PurchOrder purchOrder){
        return purchOrder.getWorkflowStatus() != WorkflowStatus.APPROVED
                && purchOrder.getWorkflowStatus() != WorkflowStatus.CANCELED;
    }

    @Override
    public boolean rejects(User user, PurchOrder purchOrder){
        if(purchOrder.getWorkflowStatus() == WorkflowStatus.APPROVED
                && purchOrder.getWorkflowStatus() == WorkflowStatus.CANCELED
                && purchOrder.getWorkflowStatus() == WorkflowStatus.DRAFT) {
            return false;
        }

        switch (user.getRole()) {
            case DIRECTOR -> {
                return true;
            }
            case MANAGER -> {
                return purchOrder.getWorkflowStatus() != WorkflowStatus.DIRECTOR_APPROVAL;
            }
            case ANALIST -> {
                return purchOrder.getWorkflowStatus() == WorkflowStatus.ANALIST_APPROVAL
                        || purchOrder.getWorkflowStatus() == WorkflowStatus.SENDED;
            }
            case ASSISTANT -> {
                return purchOrder.getWorkflowStatus() == WorkflowStatus.SENDED;
            }
        }

        return false;
    }

    public static Approval link(Approval first, Approval... chain) {
        Approval head = first;
        for (Approval nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    protected boolean approvesNext(User user, PurchOrder purchOrder) {
        if (next == null) {
            return true;
        }
        return next.approves(user, purchOrder);
    }
}
