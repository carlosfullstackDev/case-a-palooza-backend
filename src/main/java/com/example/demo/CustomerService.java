package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public void updateCustomer(Long customerId, Customers customers) {

        Optional<Customers> customer = repository.findById(customerId);

        if(customer.isPresent()){
            customer.get().setFirstName(customers.getFirstName());
            customer.get().setEmail(customers.getEmail());
            customer.get().setLastName(customers.getLastName());

            repository.save(customer.get());
        }
        else{

            //todo add a response entity in this part
            System.out.println("Error user does not exists");
        }

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
