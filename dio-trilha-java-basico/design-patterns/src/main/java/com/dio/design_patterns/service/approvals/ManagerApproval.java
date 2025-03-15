package com.dio.design_patterns.service.approvals;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.entity.User;
import com.dio.design_patterns.entity.enums.Role;
import com.dio.design_patterns.entity.enums.WorkflowStatus;
import com.dio.design_patterns.service.approvals.interfaces.CheckUserApproval;

import java.math.BigDecimal;

public class ManagerApproval extends Approval implements CheckUserApproval {
    @Override
    public boolean check(User user) {
        return user.getRole() == Role.MANAGER
                || user.getRole() == Role.DIRECTOR;
    }

    @Override
    public boolean approves(User user, PurchOrder purchOrder) {
        if (!super.approves(user, purchOrder)) {
            return false;
        }
        logger.info("3 - Etapa de Aprovação Nível Gerência");
        if (purchOrder.getPurchAmount().compareTo(BigDecimal.valueOf(50000)) < 0) {
            if (check(user)) {
                logger.info("OC {} aprovada na etapa {}.", purchOrder.getId(), WorkflowStatus.MANAGER_APPROVAL);
                return true;
            }
            else {
                logger.info("OC {}. O usuário não tem permissão para aprovar {}.", purchOrder.getId(), WorkflowStatus.MANAGER_APPROVAL);
                return false;
            }
        }
        logger.warn("OC {} encaminhada para próxima etapa do fluxo", purchOrder.getId());
        purchOrder.setWorkflowStatus(WorkflowStatus.DIRECTOR_APPROVAL);
        return approvesNext(user, purchOrder);
    }
}
