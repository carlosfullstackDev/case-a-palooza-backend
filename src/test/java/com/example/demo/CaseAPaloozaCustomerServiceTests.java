package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CaseAPaloozaCustomerServiceTests {


    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @Test
    public void testUpdateCustomer() {
        Long customerId = 1L;
        Customers customer = new Customers.Builder().firstName("John").lastName("Doe").email("johndoe@example.com").build();

        Customers existingCustomer = new Customers.Builder().firstName("Jane").lastName("Doe").email("janedoe@example.com").build();

        when(repository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(repository.save(existingCustomer)).thenReturn(existingCustomer);

        ResponseEntity<Customers> response = service.updateCustomer(customerId, customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingCustomer.getFirstName(), customer.getFirstName());
        assertEquals(existingCustomer.getLastName(), customer.getLastName());
        assertEquals(existingCustomer.getEmail(), customer.getEmail());
    }

    @Test
    public void testUpdateCustomerNotFound() {
        Long customerId = 1L;
        Customers customer = new Customers.Builder().firstName("John").lastName("Doe").email("johndoe@example.com").build();

        when(repository.findById(customerId)).thenReturn(Optional.empty());

        ResponseEntity<Customers> response = service.updateCustomer(customerId, customer);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteCustomer() {
        Long customerId = 1L;

        doNothing().when(repository).deleteById(customerId);

        service.deleteCustomer(customerId);

        verify(repository, times(1)).deleteById(customerId);
    }

    @Test
    public void testCreateCustomer() {
        Customers customer = new Customers.Builder().firstName("John").lastName("Doe").email("johndoe@example.com").build();

        Example<Customers> example = Example.of(customer);

        when(repository.findOne(example)).thenReturn(Optional.empty());
        when(repository.save(customer)).thenReturn(customer);

        Optional<Customers> response = service.createCustomer(customer);

        assertTrue(response.isPresent());
        assertEquals(customer, response.get());
    }

    @Test
    public void testCreateCustomerAlreadyExists() {
        Customers customer = new Customers.Builder().firstName("John").lastName("Doe").email("johndoe@example.com").build();

        Example<Customers> example = Example.of(customer);

        when(repository.findOne(example)).thenReturn(Optional.of(customer));

        Optional<Customers> response = service.createCustomer(customer);

        assertFalse(response.isPresent());
    }

    @Test
    public void testGetCustomerById() {
        Long customerId = 1L;

        Customers customer = new Customers.Builder().firstName("John").lastName("Doe").email("johndoe@example.com").build();

        when(repository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customers> response = service.getCustomerById(customerId);

        assertTrue(response.isPresent());
        assertEquals(customer, response.get());
    }


    @Test
    public void testGetAllCustomers() throws Exception {
        Customers customer = new Customers.Builder().firstName("Ash").lastName("Ketchum").email("ashketchum@example.com").build();
        Customers customer2 = new Customers.Builder().firstName("Lillie").lastName("Aether").email("lillieaether@example.com").build();



        List<Customers> customers = Arrays.asList(
                customer,customer2
        );

        when(repository.findAll()).thenReturn(customers);

        List<Customers> response = service.getAllCustomers();

        assertEquals(customers, response);
    }

    @Test(expected = Exception.class)
    public void testGetAllCustomersEmptyList() throws Exception {
        List<Customers> customers = Collections.emptyList();

        when(repository.findAll()).thenReturn(customers);

        service.getAllCustomers();
    }

}
