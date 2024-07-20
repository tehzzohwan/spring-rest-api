package com.tehzzcode.bezexample.repository;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.payload.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findBySubscribe(boolean subscribe);
    List<Customer> findByNameContaining(String name);
}
