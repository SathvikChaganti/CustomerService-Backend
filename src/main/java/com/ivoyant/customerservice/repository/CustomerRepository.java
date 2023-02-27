package com.ivoyant.customerservice.repository;

import com.ivoyant.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   Customer findByBillingAccountNumber(int billingAccountNumber);

}
