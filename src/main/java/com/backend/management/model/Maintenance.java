package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.bytebuddy.asm.Advice;
import org.jboss.jandex.Main;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "maintenance")
@JsonDeserialize(builder = Maintenance.Builder.class)
public class Maintenance {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long maintenance_order_id;
    @JsonProperty("process_date")
    private LocalDate processedDate;
    @JsonProperty("complete_date")
    private LocalDate completeDate;
    @JsonProperty("provider_note")
    private String providerNote;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User provider;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    public Maintenance(){}

    private Maintenance(Builder builder){
        this.maintenance_order_id = builder.maintenance_order_id;
        this.processedDate = builder.processedDate;
        this.completeDate = builder.completeDate;
        this.providerNote = builder.providerNote;
        this.provider = builder.provider;
        this.order = builder.order;
    }

    public Long getMaintenance_order_id() {
        return maintenance_order_id;
    }

    public LocalDate getProcessedDate() {
        return processedDate;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public User getProvider() {
        return provider;
    }

    public Maintenance setProvider(User provider) {
        this.provider = provider;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public static class Builder{
        private Long maintenance_order_id;
        private LocalDate processedDate;
        private LocalDate completeDate;
        private String providerNote;
        private User provider;
        private Order order;

        public Builder setId(Long maintenance_order_id) {
            this.maintenance_order_id = maintenance_order_id;
            return this;
        }

        public Builder setProcessedDate(LocalDate processed_date) {
            this.processedDate = processed_date;
            return this;
        }

        public Builder setCompleteDate(LocalDate complete_date) {
            this.completeDate = complete_date;
            return this;
        }

        public Builder setProviderNote(String providerNote) {
            this.providerNote = providerNote;
            return this;
        }

        public Builder setProvider(User provider) {
            this.provider = provider;
            return this;
        }

        public Maintenance build() {
            return new Maintenance(this);
        }
    }
}
