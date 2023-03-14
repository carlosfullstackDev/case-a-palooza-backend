package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    CustomerRepository repository;

    public  ResponseEntity<Customers>  updateCustomer(Long customerId, Customers customers) {

        Optional<Customers> customer = repository.findById(customerId);

        if(customer.isPresent()){
            customer.get().setFirstName(customers.getFirstName());
            customer.get().setEmail(customers.getEmail());
            customer.get().setLastName(customers.getLastName());

            repository.save(customer.get());
            return new ResponseEntity<>(HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public void deleteCustomer(Long customerId) {
        repository.deleteById(customerId);
    }


    public Optional<Customers> createCustomer(Customers customers) {

        Example<Customers> example = Example.of(customers);

        Optional<Customers> customer = repository.findOne(example);

        if(!customer.isPresent()){

            return Optional.of(repository.save(customers));

        }
        else {
                return Optional.empty();
        }
    }

    public Optional<Customers> getCustomerById(Long customerId) {

        return repository.findById(customerId);
    }


    public List<Customers> getAllCustomers() throws  Exception {

        if(repository.findAll().isEmpty()){
            throw  new Exception();
        }else {
            return repository.findAll();
        }

    }


}
