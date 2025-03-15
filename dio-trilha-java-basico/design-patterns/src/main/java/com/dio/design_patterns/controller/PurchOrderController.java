package com.dio.design_patterns.controller;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.service.PurchOrderService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchOrderController {
    private final PurchOrderService purchOrderService;

    public PurchOrderController(PurchOrderService purchOrderService) {
        this.purchOrderService = purchOrderService;
    }

    @PostMapping
    public PurchOrder save(@RequestBody PurchOrder purchOrder) {
        return purchOrderService.save(purchOrder);
    }

    @GetMapping
    public List<PurchOrder> findAll() {
        return purchOrderService.findAll();
    }

    @PatchMapping("/workflow/approve/{id}")
    public PurchOrder workflowApprove(@PathVariable Long id) {
        PurchOrder po = purchOrderService.findById(id).orElseThrow(RuntimeException::new);
        return purchOrderService.workflowApprove(po);
    }

    @PatchMapping("workflow/send/{id}")
    public PurchOrder workflowSend(@PathVariable Long id) {
        PurchOrder po = purchOrderService.findById(id).orElseThrow(RuntimeException::new);
        return purchOrderService.workflowSend(po);
    }

    @PatchMapping("workflow/draft/{id}")
    public PurchOrder workflowDraft(@PathVariable Long id) {
        PurchOrder po = purchOrderService.findById(id).orElseThrow(RuntimeException::new);
        return purchOrderService.workflowDraft(po);
    }

    @PatchMapping("workflow/reject/{id}")
    public PurchOrder workflowReject(@PathVariable Long id) {
        PurchOrder po = purchOrderService.findById(id).orElseThrow(RuntimeException::new);
        return purchOrderService.workflowReject(po);
    }

    @PatchMapping("workflow/cancel/{id}")
    public PurchOrder workflowCancel(@PathVariable Long id) {
        PurchOrder po = purchOrderService.findById(id).orElseThrow(RuntimeException::new);
        return purchOrderService.workflowCancel(po);
    }
}
