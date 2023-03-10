package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doThrow;
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

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customers> customers = new ArrayList<>();
        customers.add( new Customers.Builder()
                .id(1L)
                .firstName("John Doe")
                .build());
        customers.add(new Customers.Builder()
                .id(2L)
                .firstName("Lillie")
                .build());

        Mockito.when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customers>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    public void testGetAllCustomers_ThrowsException() throws Exception {

        // Setup
        Mockito.when(customerService.getAllCustomers()).thenThrow(new Exception());

        // Execute
        ResponseEntity<List<Customers>> response = customerController.getAllCustomers();

        // Verify
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testCreateCustomer() throws Exception {
        Customers customer = new Customers.Builder()
                .id(1L)
                .firstName("John Doe")
                .build();;

        Mockito.when(customerService.createCustomer(Mockito.any(Customers.class))).thenReturn(Optional.of(customer));

        ResponseEntity<Customers> response = customerController.createCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testCreateCustomerBadRequest() throws Exception {
        Customers customer = new Customers.Builder()
                .id(1L)
                .firstName("John Doe")
                .build();

        Mockito.when(customerService.createCustomer(Mockito.any(Customers.class))).thenReturn(Optional.empty());

        ResponseEntity<Customers> response = customerController.createCustomer(customer);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customers customer = new Customers.Builder()
                .id(1L)
                .firstName("John Doe")
                .build();

        Mockito.when(customerService.updateCustomer(Mockito.anyLong(), Mockito.any(Customers.class))).thenReturn(new ResponseEntity<>(customer, HttpStatus.OK));

        ResponseEntity<Customers> response = customerController.updateCustomer(1L, customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        ResponseEntity<Customers> response = customerController.deleteCustomer(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }


}
