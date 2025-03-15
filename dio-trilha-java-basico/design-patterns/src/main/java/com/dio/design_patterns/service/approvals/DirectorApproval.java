package com.dio.design_patterns.service.approvals;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.entity.User;
import com.dio.design_patterns.entity.enums.Role;
import com.dio.design_patterns.entity.enums.WorkflowStatus;
import com.dio.design_patterns.service.approvals.interfaces.CheckUserApproval;

public class DirectorApproval extends Approval implements CheckUserApproval {
    @Override
    public boolean check(User user) {
        return user.getRole() == Role.DIRECTOR;
    }
    @Override
    public boolean approves(User user, PurchOrder purchOrder) {
        if (!super.approves(user, purchOrder)) {
            return false;
        }
        logger.info("4 - Etapa de Aprovação Nível Diretoria");
        if (check(user)) {
            logger.info("OC {} aprovada na etapa {}.", purchOrder.getId(), WorkflowStatus.DIRECTOR_APPROVAL);
            return true;
        }
        logger.info("OC {}. O usuário não tem permissão para aprovar {}.", purchOrder.getId(), WorkflowStatus.DIRECTOR_APPROVAL);
        return false;
    }
}
