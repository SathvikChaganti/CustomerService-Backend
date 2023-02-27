package com.ivoyant.customerservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull(message = "addressLine1 shoudn't be null")
    private String addressLine1;
    @NotBlank
    @NotNull(message = "addressLine2 shoudn't be null")
    private String addressLine2;
    @NotBlank
    @NotNull(message = "city shoudn't be null")
    private String city;
    @NotBlank
    @Pattern( regexp = "^\\d{5}$", message = "invalid zip code")
    private String zipCode;
    @NotBlank
    @NotNull(message = "state shoudn't be null")
    private String state;
    @OneToOne(mappedBy = "address")
    private Customer customer;

    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
