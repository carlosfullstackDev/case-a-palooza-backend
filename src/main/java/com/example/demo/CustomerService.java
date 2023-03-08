package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Customers updateCustomer(Long customerId, Customers customers) {

    return null;
    }

    public Customers deleteCustomer(Long customerId) {
        return null;
    }


    public Customers createCustomer(Customers customers) {

        return repository.save(customers);
    }

    public Customers getCustomerById(Long customerId) {

        return repository.findById(customerId).get();
    }


}
