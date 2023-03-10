package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Customers> getCustomer(@PathVariable Long customerId) {
        Optional<Customers> customers = customerService.getCustomerById(customerId);
        if (!customers.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers.get(), HttpStatus.OK);
    }



    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customers>> getAllCustomers() {
        try {
            List<Customers> customers = customerService.getAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers customers) {
        Optional<Customers> createdCustomers = customerService.createCustomer(customers);

        return createdCustomers.map(customer -> new ResponseEntity<>(customer, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable Long customerId, @RequestBody Customers customers) {
        ResponseEntity<Customers>  response = customerService.updateCustomer(customerId, customers);

        return response;
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customers> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
