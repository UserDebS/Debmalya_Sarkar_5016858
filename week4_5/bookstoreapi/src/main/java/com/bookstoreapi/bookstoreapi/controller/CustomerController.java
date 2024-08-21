package com.bookstoreapi.bookstoreapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapi.bookstoreapi.entities.Customer;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.util.CustomObjectInputStream;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customerList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok().body(customerList);
    }
    
    private Customer getCustomerById(Long id) {
        return customerList.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable Long id) {
        Customer customer = getCustomerById(id);
        return (customer == null)?ResponseEntity.notFound().build() : ResponseEntity.ok().body(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
        customerList.add(customer);
        return ResponseEntity.ok().body(customer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Customer> putCustomer(@PathVariable Long id, @RequestBody Customer updCustomer) {
        Customer customer = getCustomerById(id);
        if(customer == null) return ResponseEntity.notFound().build();
        customer.setName(updCustomer.getName());
        customer.setEmail(updCustomer.getEmail());
        return 
    }
    
}
