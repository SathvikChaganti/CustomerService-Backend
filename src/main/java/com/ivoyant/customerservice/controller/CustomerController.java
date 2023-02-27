package com.ivoyant.customerservice.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ivoyant.customerservice.model.Customer;
import com.ivoyant.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

//    @Autowired
//    JmsConfig jmsConfig;

    @PostMapping(value = "/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws JsonProcessingException {
//        jmsConfig.sendEvent ("CustomerController","createCustomer", new ObjectMapper ().writeValueAsString (customer));
       return new ResponseEntity<> (customerService.createCustomer( customer ),HttpStatus.OK);
    }

    @GetMapping(value = "/getcustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{billingAccountNumber}")
    public ResponseEntity<Object> getCustomer(@PathVariable int billingAccountNumber) {
        return new ResponseEntity<>(customerService.getCustomer( billingAccountNumber), HttpStatus.OK);
    }

    @PutMapping("/{billingAccountNumber}")
    public Customer updateCustomer(@PathVariable int billingAccountNumber, @RequestBody Customer update) throws JsonProcessingException {
//        jmsConfig.sendEvent ("CustomerController","updateCustomer", new ObjectMapper ().writeValueAsString (update));
        return customerService.updateCustomer(billingAccountNumber, update);
    }

    @DeleteMapping("/{billingAccountNumber}")
    public void deleteCustomerByBillingAccountNumber(@PathVariable String billingAccountNumber) {
//        jmsConfig.sendEvent ("CustomerController","deleteCustomerByBillingAccountNumber", String.valueOf (billingAccountNumber));
        customerService.deleteCustomer(billingAccountNumber);
    }
}



