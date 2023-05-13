package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "user")
@JsonDeserialize(builder = User.Builder.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String username;


    @JsonIgnore
    private String password;


    @JsonIgnore
    private boolean enabled;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_number")
    private Apartment apartmentNumber;

    public User() {}

    private User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.enabled = builder.enabled;
        this.apartmentNumber = builder.apartment_number;
    }



    public String getUsername() {
        return username;
    }


    public Apartment getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Apartment apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getPassword() {
        return password;
    }


    public User setPassword(String password) {
        this.password = password;
        return this;
    }


    public boolean isEnabled() {
        return enabled;
    }


    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }


    public static class Builder {
        @JsonProperty("username")
        private String username;


        @JsonProperty("password")
        private String password;


        @JsonProperty("enabled")
        private boolean enabled;

        @JsonProperty("apartment_number")
        private Apartment apartment_number;



        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }


        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }


        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }


        public User build() {
            return new User(this);
        }
    }

}
