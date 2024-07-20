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
    public ResponseEntity<DefaultResponseDto> getAllCustomers(@RequestParam(required = false) String name) {
        DefaultResponseDto response = new DefaultResponseDto();
        List<Customer> customers = new ArrayList<Customer>();
        response.setStatusCode("00");

        try {
            if (name == null) {
                customers.addAll(customerService.getAllCustomers());
                response.setMessage(customers.size() + " customers successfully fetched");
            } else {
                customers.addAll(customerService.getAllCustomersByName(name));
                response.setMessage(customers.size() + " customers with the name: " + name + ", successfully fetched");
            }
            response.setData(customers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatusCode("99");
            response.setMessage( "Sorry something went wrong");
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
    public ResponseEntity<DefaultResponseDto> getCustomerById(@PathVariable("id") long id) {
        DefaultResponseDto response = new DefaultResponseDto();
        response.setStatusCode("00");

        try {
            Optional<Customer> customer = customerService.getCustomerById(id);
            if (customer.isPresent()) {
                response.setMessage("Customer with id: " + id + " successfully fetched");
                response.setData(customer.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatusCode("99");
                response.setMessage("Customer with id: " + id + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatusCode("99");
            response.setMessage("Sorry something went wrong");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<DefaultResponseDto> updateCustomer(@PathVariable("id") long id,
                                                   @Valid @RequestBody CustomerDto customerDto) {
        DefaultResponseDto response = new DefaultResponseDto();
        response.setStatusCode("00");

        try {
            Optional<Customer> customer = customerService.updateCustomerById(id, customerDto);
            if (customer.isPresent()) {
                response.setMessage("Customer with id: " + id + " successfully updated");
                response.setData(customer.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatusCode("99");
                response.setMessage("Customer with id: " + id + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatusCode("99");
            response.setMessage("Sorry something went wrong");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<DefaultResponseDto> deleteCustomer(@PathVariable("id") long id) {
        DefaultResponseDto response = new DefaultResponseDto();
        response.setStatusCode("00");

        try {
            Optional<Customer> customer = customerService.getCustomerById(id);
            if (customer.isPresent()) {
                customerService.deleteCustomerById(id);
                response.setMessage("Customer with id: " + id + " successfully deleted");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatusCode("99");
                response.setMessage("Customer with id: " + id + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatusCode("99");
            response.setMessage("Sorry something went wrong");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
