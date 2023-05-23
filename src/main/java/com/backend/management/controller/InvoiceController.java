package com.backend.management.controller;

import com.backend.management.model.Invoice;
import com.backend.management.model.Order;
import com.backend.management.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
    @GetMapping(value = "/payment/all")
    public List<Invoice> getAllInvoice() {
        return invoiceService.getAllInvoice();
    }

    @GetMapping(value = "/payment")
    public List<Invoice> getByUsername(Principal principal){
        return invoiceService.getInvoiceByTenant(principal.getName());
    }

//    @PostMapping(value = "/payment/add")
//    public void addInvoice(@RequestBody Invoice invoice, @RequestParam(name = "manager_id") String managerId,
//                           @RequestParam(name = "tenant_id") String tenantId) {
//        invoiceService.add(invoice, managerId, tenantId);
//    }

    @PostMapping(value = "/payment/add/all")
    public void addAllInvoice(@RequestBody Invoice invoice, Principal principal) {
        invoiceService.addAll(invoice, principal.getName());
    }

    @PostMapping(value = "/payment/add/category")
    public void add(@RequestBody Invoice invoice, Principal principal,
                    @RequestParam(name = "apart_type") String apartType,
                    @RequestParam(name = "invoice_type") String invoiceType) {
        invoiceService.addCategory(invoice, principal.getName(), apartType, invoiceType);
    }


//    @PostMapping(value = "/payment/add/category")
//    public void add(@RequestBody Invoice invoice, @RequestParam(name = "manager_id") String managerId,
//                    @RequestParam(name = "apart_type") String apartType,
//                    @RequestParam(name = "invoice_type") String invoiceType) {
//        invoiceService.addCategory(invoice, managerId, apartType, invoiceType);
//    }

    @PostMapping(value = "/payment/update")
    public void modifyStatus(@RequestBody Invoice invoice) {
        invoiceService.modify(invoice);
    }

}
