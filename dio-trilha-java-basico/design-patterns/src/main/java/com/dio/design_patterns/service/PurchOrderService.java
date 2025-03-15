package com.dio.design_patterns.service;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.entity.User;
import com.dio.design_patterns.entity.enums.WorkflowStatus;
import com.dio.design_patterns.repository.PurchOrderRepository;
import com.dio.design_patterns.service.facade.PurchOrderFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchOrderService {
    private final PurchOrderRepository purchOrderRepository;
    private final AuthService authService;
    private final UserService userService;
    private PurchOrderFacade purchOrderFacade;

    public PurchOrderService(UserService userService, AuthService authService, PurchOrderRepository purchOrderRepository) {
        this.userService = userService;
        this.authService = authService;
        this.purchOrderRepository = purchOrderRepository;
    }

    public Optional<PurchOrder> findById (Long id) {
        return purchOrderRepository.findById(id);
    }

    public PurchOrder save(PurchOrder purchOrder) {
        return purchOrderRepository.save(purchOrder);
    }

    public List<PurchOrder> findAll() {
        return purchOrderRepository.findAll();
    }

    public PurchOrder workflowApprove(PurchOrder purchOrder) {
        User user = userService.findByUsername(authService.getAuthenticatedUser()).orElseThrow(RuntimeException::new);
        purchOrderFacade = new PurchOrderFacade(user, purchOrder);
        if (purchOrderFacade.approves()) {
            purchOrder.setWorkflowStatus(WorkflowStatus.APPROVED);
            purchOrderRepository.save(purchOrder);
        } else {
            purchOrderRepository.save(purchOrder);
        }
        return purchOrder;
    }

    public PurchOrder workflowReject(PurchOrder purchOrder) {
        // TODO - Precisa estar na etapa correta para poder aprovar
        User user = userService.findByUsername(authService.getAuthenticatedUser()).orElseThrow(RuntimeException::new);
        purchOrderFacade = new PurchOrderFacade(user, purchOrder);
        if (purchOrderFacade.rejects()) {
            purchOrder.setWorkflowStatus(WorkflowStatus.REJECTED);
            purchOrderRepository.save(purchOrder);
        }
        return purchOrder;
    }

    public PurchOrder workflowSend(PurchOrder purchOrder) {
        User user = userService.findByUsername(authService.getAuthenticatedUser()).orElseThrow(RuntimeException::new);
        purchOrderFacade = new PurchOrderFacade(user, purchOrder);
        if (purchOrderFacade.send()) {
            purchOrder.setWorkflowStatus(WorkflowStatus.SENDED);
            purchOrderRepository.save(purchOrder);
        }
        return purchOrder;
    }

    public PurchOrder workflowDraft(PurchOrder purchOrder) {
        User user = userService.findByUsername(authService.getAuthenticatedUser()).orElseThrow(RuntimeException::new);
        purchOrderFacade = new PurchOrderFacade(user, purchOrder);
        if (purchOrderFacade.draft()) {
            purchOrder.setWorkflowStatus(WorkflowStatus.DRAFT);
            purchOrderRepository.save(purchOrder);
        }
        return purchOrder;
    }

    public PurchOrder workflowCancel(PurchOrder purchOrder) {
        User user = userService.findByUsername(authService.getAuthenticatedUser()).orElseThrow(RuntimeException::new);
        purchOrderFacade = new PurchOrderFacade(user, purchOrder);
        if (purchOrderFacade.cancel()) {
            purchOrder.setWorkflowStatus(WorkflowStatus.CANCELED);
            purchOrderRepository.save(purchOrder);
        }
        return purchOrder;
    }
}
