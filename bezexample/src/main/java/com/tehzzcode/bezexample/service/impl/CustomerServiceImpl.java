package com.tehzzcode.bezexample.service.impl;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.payload.CustomerDto;
import com.tehzzcode.bezexample.repository.CustomerRepository;
import com.tehzzcode.bezexample.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getAllCustomersByName(String name) {
        return customerRepository.findByNameContaining(name);
    }

    @Override
    public Customer saveCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(customerDto.getName(), customerDto.getAge(), false);
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> updateCustomerById(long id, CustomerDto customer) {
        Optional<Customer> customerData = customerRepository.findById(id);
        if (customerData.isPresent()) {
            Customer _customer = customerData.get();
            _customer.setName(customer.getName());
            _customer.setAge(customer.getAge());
            _customer = customerRepository.save(_customer);
            return Optional.of(_customer);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteCustomerById(long id) {
        customerRepository.deleteById(id);
    }
}
