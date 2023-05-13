package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "orders")
@JsonDeserialize(builder = Order.Builder.class)
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String issueDescription;
    private String location;
    @JsonProperty("tenant_number")
    private Long tenantNumber;
    private String status;

    private String providerNote;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User tenant;


    private String provider;

    @JsonProperty("submit_date")
    private LocalDate submittedDate;
    @JsonProperty("process_date")
    private LocalDate processedDate;

    @JsonProperty("complete_date")
    private LocalDate completeDate;


    public Order(){}

    private Order(Builder builder){
        this.id = builder.id;
        this.issueDescription = builder.issueDescription;
        this.location = builder.location;
        this.tenantNumber = builder.tenantNumber;
        this.tenant = builder.tenant;
        this.status = builder.status;
        this.providerNote = builder.providerNote;
        this.provider = builder.provider;
        this.processedDate = builder.processDate;
        this.completeDate = builder.completeDate;
        this.submittedDate = builder.submittedDate;
    }

    public Long getId() {
        return id;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public String getLocation() {
        return location;
    }

    public Long getTenantNumber() {
        return tenantNumber;
    }

    public User getTenant() {
        return tenant;
    }

    public void setProviderNote(String providerNote) {
        this.providerNote = providerNote;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public void setProcessedDate(LocalDate processedDate) {
        this.processedDate = processedDate;
    }

    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("issueDescription")
        private String issueDescription;

        @JsonProperty("location")
        private String location;

        @JsonProperty("tenant_number")
        private Long tenantNumber;

        @JsonProperty("tenant")
        private User tenant;
        @JsonProperty("status")
        private String status;

        @JsonProperty("providerNote")
        private String providerNote;

        @JsonProperty("provider")
        private String provider;

        @JsonProperty("process_date")
        private LocalDate processDate;

        @JsonProperty("complete_date")
        private LocalDate completeDate;

        @JsonProperty("submit_date")
        private LocalDate submittedDate;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String issueDescription) {
            this.issueDescription = issueDescription;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setTenantNumber(Long tenantNumber) {
            this.tenantNumber = tenantNumber;
            return this;
        }

        public Builder setTenant(User tenant) {
            this.tenant = tenant;
            return this;
        }

        public Builder setStatus(String status){
            this.status = status;
            return this;
        }

        public Builder setProviderNote(String providerNote){
            this.providerNote = providerNote;
            return this;
        }

        public Builder setProvider(String provider){
            this.provider = provider;
            return this;
        }

        public Builder setProcessDate(LocalDate processDate){
            this.processDate = processDate;
            return this;
        }

        public Builder setCompleteDate(LocalDate complete_date) {
            this.completeDate = complete_date;
            return this;
        }

        public Builder setSubmittedDate(LocalDate submit_date) {
            this.submittedDate = submit_date;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
