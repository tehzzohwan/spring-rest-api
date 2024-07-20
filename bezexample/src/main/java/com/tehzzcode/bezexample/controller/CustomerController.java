package com.tehzzcode.bezexample.controller;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.payload.CustomerDto;
import com.tehzzcode.bezexample.payload.DefaultResponseDto;
import com.tehzzcode.bezexample.repository.CustomerRepository;
import com.tehzzcode.bezexample.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String name) {
        List<Customer> customers = new ArrayList<Customer>();

        try {
            if (name == null)
                customers.addAll(customerService.getAllCustomers());
            else
                customers.addAll(customerService.getAllCustomersByName(name));

            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<DefaultResponseDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        DefaultResponseDto response = new DefaultResponseDto();
        response.setStatusCode("00");

        try {
            Customer customer = customerService.saveCustomer(customerDto);
            response.setMessage("Customer created successfully with id: " + customer.getId());
            response.setData(customer);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatusCode("99");
            response.setMessage("Sorry something went wrong");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {

        try {
            Optional<Customer> customer = customerService.getCustomerById(id);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id,
                                                   @Valid @RequestBody CustomerDto customerDto) {
        try {
            Optional<Customer> customer = customerService.updateCustomerById(id, customerDto);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {

        try {
            Optional<Customer> customer = customerService.getCustomerById(id);
            if (customer.isPresent()) {
                customerService.deleteCustomerById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
    step 1: pass in the parameter of id into the deleteCustomerById method in the customerService class
    step 2: in the deleteCustomerById method the id parameter is then passed to the deleteCustomerById customerRepository class
    step 3: in the
    step : now assign the result of the customerService class to a customer variable

     */
}
