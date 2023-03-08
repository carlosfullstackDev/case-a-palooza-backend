package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Customers updateCustomer(Long customerId, Customers customers) {

    return null;
    }

    public void deleteCustomer(Long customerId) {
        repository.deleteById(customerId);
    }


    public Customers createCustomer(Customers customers) {

        return repository.save(customers);
    }

    public Optional<Customers> getCustomerById(Long customerId) {

        return repository.findById(customerId);
    }


}
