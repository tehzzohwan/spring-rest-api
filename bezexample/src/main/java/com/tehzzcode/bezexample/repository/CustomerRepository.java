package com.tehzzcode.bezexample.repository;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.entities.Role;
import com.tehzzcode.bezexample.payload.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Customer findByRole(Role role);
    List<Customer> findByNameContaining(String name);

}
