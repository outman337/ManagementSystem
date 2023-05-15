package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "apartment")
@JsonDeserialize(builder = Apartment.Builder.class)
public class Apartment implements Serializable {
    private static final long seralVersionUID = 1L;
    @Id
    private String apartmentNumber;

    @ManyToOne
    @JoinColumn(name = "apartment_type")
    private ApartmentType apartmentType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User ownerId;

    public Apartment(String apartmentNumber, ApartmentType apartmentType, User ownerId) {
        this.apartmentNumber = apartmentNumber;
        this.apartmentType = apartmentType;
        this.ownerId = ownerId;

    }

    private Apartment(Builder builder) {
        this.apartmentNumber = builder.apartmentNumber;
        this.apartmentType = builder.typeId;
        this.ownerId = builder.ownerId;

    }

    public Apartment() {

    }

    public String getApartmentId() {
        return apartmentNumber;
    }

    public void setApartmentId(String apartment_number) {
        this.apartmentNumber = apartment_number;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType typeId) {
        this.apartmentType = typeId;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User owner_id) {
        this.ownerId = owner_id;
    }

    public static class Builder {
        @JsonProperty("apartment_number")
        private String apartmentNumber;

        @JsonProperty("apartment_type")
        private ApartmentType typeId;

        @JsonProperty("owner_id")
        private User ownerId;

        public Apartment.Builder setApartmentNumber(String ApartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }
        public Apartment.Builder setTypeId(ApartmentType typeId) {
            this.typeId = typeId;
            return this;
        }
        public Apartment.Builder setOwnerId(User ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Apartment build() {
            return new Apartment(this);
        }
    }


}

