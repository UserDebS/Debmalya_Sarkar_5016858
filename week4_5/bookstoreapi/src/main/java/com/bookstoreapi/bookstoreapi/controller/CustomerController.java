package com.bookstoreapi.bookstoreapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapi.bookstoreapi.DTOs.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entities.Customer;
import com.bookstoreapi.bookstoreapi.mapper.CustomerMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customerList = new ArrayList<>();

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return ResponseEntity.ok().body(customerList.stream().map(customerMapper::toCustomerDTO).collect(Collectors.toList()));
    }
    
    private Customer getCustomerById(Long id) {
        return customerList.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable Long id) {
        Customer customer = getCustomerById(id);
        return (customer == null)?ResponseEntity.notFound().build() : ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO postCustomer(@RequestBody CustomerDTO customer) {
        customerList.add(customerMapper.toCustomer(customer));
        return customer;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> putCustomer(@PathVariable Long id, @RequestBody CustomerDTO updCustomer) {
        Customer customer = getCustomerById(id);
        if(customer == null) return ResponseEntity.notFound().build();
        Customer updatedCustomer = customerMapper.toCustomer(updCustomer);
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Long id) {
        Customer customer = getCustomerById(id);
        if(customer == null) return ResponseEntity.notFound().build();
        customerList.remove(customer);
        return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
    }
    
}
