package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")
@JsonDeserialize(builder = Invoice.Builder.class)
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceID;
    private String invoiceName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", insertable = false, updatable = false)
    @JsonIgnore
    private User manager;
    @ManyToOne
    @JoinColumn(name = "username")
    //@JsonManagedReference
    //@JsonIgnore
    private User tenant;
    private double amount;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;

    public Invoice() {}
    private Invoice(Builder builder) {
        this.invoiceID = builder.invoiceID;
        this.invoiceName = builder.invoiceName;
        this.manager = builder.manager;
        this.tenant = builder.tenant;
        this.amount = builder.amount;
        this.invoiceDate = builder.invoiceDate;
        this.dueDate = builder.dueDate;
        this.paymentDate = builder.paymentDate;
    }

    public static class Builder {
        @JsonProperty("invoiceID")
        private Long invoiceID;
        @JsonProperty("invoiceName")
        private String invoiceName;
        @JsonProperty("manager")
        private User manager;
        @JsonProperty("tenant")
        private User tenant;
        @JsonProperty("amount")
        private double amount;
        @JsonProperty("invoiceDate")
        private LocalDateTime invoiceDate;
        @JsonProperty("dueDate")
        private LocalDateTime dueDate;
        @JsonProperty("paymentDate")
        private LocalDateTime paymentDate;

        public Builder setInvoiceID(Long invoiceID) {
            this.invoiceID = invoiceID;
            return this;
        }

        public Builder setInvoiceName(String invoiceName) {
            this.invoiceName = invoiceName;
            return this;
        }

        public Builder setManager(User manager) {
            this.manager = manager;
            return this;
        }

        public Builder setTenant(User tenant) {
            this.tenant = tenant;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setInvoiceDate(LocalDateTime invoiceDate) {
            this.invoiceDate = invoiceDate;
            return this;
        }

        public Builder setDueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder setPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }

    public Long getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Long invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    //public User getTenant() {
        //return tenant;
    //}

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }
    public String getTenant() {
        return tenant.getUsername();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
