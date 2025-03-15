package com.dio.design_patterns.service.approvals.interfaces;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.entity.User;

public interface ApprovalHandler {
    boolean approves(User user, PurchOrder purchOrder);
    boolean rejects(User user, PurchOrder purchOrder);
    boolean draft(PurchOrder purchOrder);
    boolean cancel(PurchOrder purchOrder);
    boolean send(PurchOrder purchOrder);
}
