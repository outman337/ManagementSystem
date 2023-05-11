package com.backend.management.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "apartment_type")
public class ApartmentType implements Serializable {
    @Id
    private String typeId;

    private int capacity;

    private BigDecimal rent;
    private BigDecimal management_fee;


    public ApartmentType(String typeId, int capacity, BigDecimal rent, BigDecimal management_fee) {
        this.typeId = typeId;
        this.capacity = capacity;
        this.rent = rent;
        this.management_fee = management_fee;
    }

    public ApartmentType() {

    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String type_id) {
        this.typeId = type_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getManagementFee() {
        return management_fee;
    }

    public void setManagementFee(BigDecimal management_fee) {
        this.management_fee = management_fee;
    }
}
