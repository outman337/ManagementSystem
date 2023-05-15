package com.backend.management.service;

import com.backend.management.exception.InvoiceNotExistException;
import com.backend.management.exception.MoveException;
import com.backend.management.exception.UserNotExistException;
import com.backend.management.model.Apartment;
import com.backend.management.model.Invoice;
import com.backend.management.model.User;
import com.backend.management.repository.ApartmentRepository;
import com.backend.management.repository.InvoiceRepository;
import com.backend.management.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private UserRepository userRepository;
    private ApartmentRepository apartmentRepository;
    public InvoiceService(InvoiceRepository invoiceRepository, UserRepository userRepository,
                          ApartmentRepository apartmentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public void add(Invoice invoice, String managerId, String tenantId) {
        if (invoice != null) {
            Optional<User> userOptional = userRepository.findById(managerId);
            if (userOptional.isEmpty()) {
                // TODO: handle this error.
                throw new UserNotExistException("Invalid manager!");
            }
            User manager = userOptional.get();

            userOptional = null;
            userOptional = userRepository.findById(tenantId);
            if (userOptional.isEmpty()) {
                // TODO: handle this error.
                throw new UserNotExistException("Invalid tenant!");
            }
            User tenant = userOptional.get();

            invoice.setInvoiceDate(LocalDateTime.now());
            invoice.setManager(manager);
            invoice.setTenant(tenant);
            invoiceRepository.save(invoice);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void modify(Invoice invoice) throws InvoiceNotExistException {
        Invoice curr = invoiceRepository.findByinvoiceID(invoice.getInvoiceID());
        if (curr == null) {
            throw new InvoiceNotExistException("Invoice doesn't exist");
        }
        LocalDateTime paymentDate = LocalDateTime.now();
        invoiceRepository.updateInvoice(curr.getInvoiceID(), paymentDate);
    }

    public List<Invoice> getInvoiceByTenant(String username) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            // TODO: handle this error.
            throw new MoveException("Invalid Username!");
        }
        User user = userOptional.get();
        return invoiceRepository.findByTenant(user);
    }

    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    public void addAll(Invoice invoice, String managerId) {
        if (invoice != null) {
            List<Apartment> apartments = apartmentRepository.findAll();
            for (Apartment apartment : apartments) {
                if (apartment.getOwnerId() != null) {
                    Invoice newInvoice = new Invoice();
                    newInvoice.setInvoiceName(invoice.getInvoiceName());
                    newInvoice.setDueDate(invoice.getDueDate());
                    newInvoice.setAmount(invoice.getAmount());
                    add(newInvoice, managerId, apartment.getOwnerId().getUsername());
                }
            }
        }
    }
}
