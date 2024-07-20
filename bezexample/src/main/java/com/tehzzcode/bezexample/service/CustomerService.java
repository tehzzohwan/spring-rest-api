package com.tehzzcode.bezexample.service;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.payload.CustomerDto;
import com.tehzzcode.bezexample.payload.DefaultResponseDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    List<Customer> getAllCustomersByName(String name);
    Customer saveCustomer(@Valid CustomerDto customerDto);
    Optional<Customer> getCustomerById(long id);
    Optional<Customer> updateCustomerById(long id, CustomerDto customer);
    void deleteCustomerById(long id);
}
