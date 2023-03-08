package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Customers> getCustomer(@PathVariable Long customerId) {
        Customers customers = customerService.getCustomerById(customerId);
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers customers) {
        Customers createdCustomers = customerService.createCustomer(customers);
        return new ResponseEntity<>(createdCustomers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable Long customerId, @RequestBody Customers customers) {
        Customers updatedCustomers = customerService.updateCustomer(customerId, customers);
        if (updatedCustomers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCustomers, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customers> deleteCustomer(@PathVariable Long customerId) {
        Customers deletedCustomers = customerService.deleteCustomer(customerId);
        if (deletedCustomers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedCustomers, HttpStatus.OK);
    }

}
