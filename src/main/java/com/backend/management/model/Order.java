package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
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
    private int tenantNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User tenant;
    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<OrderSubmittedDate> submittedDates;

    public Order(){}

    private Order(Builder builder){
        this.id = builder.id;
        this.issueDescription = builder.issueDescription;
        this.location = builder.location;
        this.tenantNumber = builder.tenantNumber;
        this.tenant = builder.tenant;
        this.submittedDates = builder.submittedDates;
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

    public int getTenantNumber() {
        return tenantNumber;
    }

    public User getTenant() {
        return tenant;
    }

    public List<OrderSubmittedDate> getSubmittedDates() {
        return submittedDates;
    }

    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("issueDescription")
        private String issueDescription;

        @JsonProperty("location")
        private String location;

        @JsonProperty("tenant_number")
        private int tenantNumber;

        @JsonProperty("tenant")
        private User tenant;

        @JsonProperty("dates")
        private List<OrderSubmittedDate> submittedDates;

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

        public Builder setTenantNumber(int tenantNumber) {
            this.tenantNumber = tenantNumber;
            return this;
        }

        public Builder setTenant(User tenant) {
            this.tenant = tenant;
            return this;
        }

        public Builder setRSubmittedDates(List<OrderSubmittedDate> submittedDates) {
            this.submittedDates = submittedDates;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
