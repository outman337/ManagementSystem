package com.backend.management.repository;

import com.backend.management.model.Invoice;
import com.backend.management.model.Order;
import com.backend.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByTenant(User tenant);
    List<Invoice> findAll();
    Invoice findByinvoiceID(Long id);
    @Modifying
    @Query("UPDATE Invoice i SET i.paymentDate = :paymentDate WHERE i.invoiceID = :invoiceID")
    void updateInvoice(@Param("invoiceID") Long invoiceID, @Param("paymentDate") LocalDateTime paymentDate);

}
