package com.ivoyant.customerservice.model;

import com.fasterxml.jackson.core.JsonFactory;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @NotNull(message = "firstName shoudn't be null")
    private String firstName;
    @NotBlank
    @NotNull(message = "lastName shoudn't be null")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @Pattern( regexp = "^\\d{10}$", message = "invalid phone number")
    private String phoneNumber;

    @Email(message  = "invalid emailId")
    private String emailId;

    private int billingAccountNumber;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void listAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getBillingAccountNumber() {
        return billingAccountNumber;
    }

    public void setBillingAccountNumber(int billingAccountNumber) {
        this.billingAccountNumber = billingAccountNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
