package com.ivoyant.customerservice.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivoyant.customerservice.JmsConfig;
import com.ivoyant.customerservice.config.StateConversion;
import com.ivoyant.customerservice.exception.CustomerNotFoundException;
import com.ivoyant.customerservice.exception.InvalidPhoneNumberException;
import com.ivoyant.customerservice.exception.InvalidZipCodeException;
import com.ivoyant.customerservice.model.Address;
import com.ivoyant.customerservice.model.Customer;
import com.ivoyant.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.*;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    StateConversion stateConversion;

    @Autowired
    JmsConfig jmsConfig;

    public Customer createCustomer(Customer customer) throws InvalidZipCodeException, InvalidPhoneNumberException, JsonProcessingException {
        if (!isValidZipCode(customer.getAddress().getZipCode())) {
            throw new InvalidZipCodeException("Invalid zip code provided");
        }
        if (!isValidPhoneNumber(customer.getPhoneNumber())) {
            throw new InvalidPhoneNumberException("Invalid phone number provided");
        }
        SecureRandom random = new SecureRandom();
        int accountNumber = random.nextInt(999999999) + 10000000;
        customer.setBillingAccountNumber(accountNumber);
        Address address = customer.getAddress ();
        address.setState (stateConversion.convertState (address.getState ()));
        Customer c = customerRepository.save(customer);
        jmsConfig.sendEvent ("Customer","createCustomer", new ObjectMapper ().writeValueAsString (c));
        return c;
    }


    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(int billingAccountNumber) {
        Customer customer = customerRepository.findByBillingAccountNumber(billingAccountNumber);
             if(customer == null) {
                 throw new CustomerNotFoundException ("No customer found with account number "+ billingAccountNumber);
             }
        return customer;
    }

    public Customer updateCustomer(int billingAccountNumber, Customer update) throws JsonProcessingException {
        Customer c = customerRepository.findByBillingAccountNumber( billingAccountNumber );
        c.getAddress().setState(c.getAddress().getState());
        c.setAddress(update.getAddress());
        c.setEmailId(update.getEmailId());
        customerRepository.save(c);
        Customer c1 = customerRepository.save(c);
        return jmsConfig.sendEvent ("Customer","updateCustomer", new ObjectMapper ().writeValueAsString (c1));
    }

    public void deleteCustomer(String billingAccountNumber) {
        Customer customer = customerRepository.findByBillingAccountNumber(Integer.parseInt (billingAccountNumber));
        if(customer == null) {
            throw new CustomerNotFoundException ("No customer found with account number "+ billingAccountNumber);
        }
        jmsConfig.sendEvent ("CustomerController","deleteCustomerByBillingAccountNumber", billingAccountNumber);
        customerRepository.delete(customer);
    }

    private boolean isValidZipCode(String zip) {
        if (!zip.matches("\\d{5}")) {
            return false;
        }
        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\d{10}")) {
            return false;
        }
        return true;
    }
}