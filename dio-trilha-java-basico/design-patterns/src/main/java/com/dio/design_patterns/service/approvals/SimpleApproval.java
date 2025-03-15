package com.dio.design_patterns.service.approvals;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.entity.User;
import com.dio.design_patterns.entity.enums.WorkflowStatus;

import java.math.BigDecimal;

public class SimpleApproval extends Approval{
    @Override
    public boolean approves(User user, PurchOrder purchOrder) {
        if (!super.approves(user, purchOrder)) {
            return false;
        }
        logger.info("1 - Etapa de Aprovação Simples");
        if (purchOrder.getPurchAmount().compareTo(BigDecimal.valueOf(1000)) < 0) {
            logger.info("OC {} aprovada em etapa simples.", purchOrder.getId());
            return true;
        }
        logger.warn("OC {} encaminhada para próxima etapa do fluxo {}", purchOrder.getId(), WorkflowStatus.ANALIST_APPROVAL);
        purchOrder.setWorkflowStatus(WorkflowStatus.ANALIST_APPROVAL);
        return approvesNext(user, purchOrder);
    }
}
