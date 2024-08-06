package com.tehzzcode.bezexample.service;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.payload.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerServize {
    List<Customer> getAllCustomers();
    List<Customer> getAllCustomersByName(String name);
    Optional<Customer> getCustomerById(long id);
    Optional<Customer> updateCustomerById(long id, CustomerDto customer);
    void deleteCustomerById(long id);
}
