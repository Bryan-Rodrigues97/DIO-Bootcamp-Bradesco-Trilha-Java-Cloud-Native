package com.dio.design_patterns.service.facade;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.entity.User;
import com.dio.design_patterns.service.approvals.*;

public class PurchOrderFacade {
    private final User user;
    private final PurchOrder purchOrder;
    private Approval approval;

    public PurchOrderFacade(User user, PurchOrder purchOrder) {
        this.user = user;
        this.purchOrder = purchOrder;
        initApproval();
    }

    private void initApproval() {
        approval = Approval.link(
            new SimpleApproval(),
            new AnalistApproval(),
            new ManagerApproval(),
            new DirectorApproval()
        );
    }

    public boolean approves() {
        return approval.approves(user, purchOrder);
    }

    public boolean rejects() { return approval.rejects(user, purchOrder); }

    public boolean draft() {
        return approval.draft(purchOrder);
    }

    public boolean cancel() {
        return approval.cancel(purchOrder);
    }

    public boolean send() { return approval.send(purchOrder); }
}
