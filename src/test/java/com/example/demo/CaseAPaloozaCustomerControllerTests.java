package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CaseAPaloozaCustomerControllerTests {


    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void testGetCustomerById_whenCustomerExists() {
        Long customerId = 1L;
        Customers customer = new Customers.Builder()
                .id(customerId)
                .firstName("John Doe")
                .build();

        Optional<Customers> optionalCustomer = Optional.of(customer);

        when(customerService.getCustomerById(customerId)).thenReturn(optionalCustomer);

        ResponseEntity<Customers> responseEntity = customerController.getCustomer(customerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customer, responseEntity.getBody());
    }

    @Test
    public void testGetCustomerById_whenCustomerDoesNotExist() {
        Long customerId = 1L;
        Optional<Customers> optionalCustomer = Optional.empty();

        when(customerService.getCustomerById(customerId)).thenReturn(optionalCustomer);

        ResponseEntity<Customers> responseEntity = customerController.getCustomer(customerId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
