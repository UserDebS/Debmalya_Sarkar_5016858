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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<EntityModel<CustomerDTO>>> getCustomers() {
        return ResponseEntity.ok().body(customerList.stream().map(customer -> {
            CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
            EntityModel<CustomerDTO> model = EntityModel.of(customerDTO);
            Link selflink = WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customer.getId()).withSelfRel();
            model.add(selflink);
            return model;
        }).collect(Collectors.toList()));
    }
    
    private Customer getCustomerById(Long id) {
        return customerList.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable Long id) {
        Customer customer = getCustomerById(id);
        return (customer == null)?ResponseEntity.notFound().build() : ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO postCustomer(@RequestBody CustomerDTO customer) {
        customerList.add(customerMapper.toCustomer(customer));
        return customer;
    }
    
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerDTO> putCustomer(@PathVariable Long id, @RequestBody CustomerDTO updCustomer) {
        Customer customer = getCustomerById(id);
        if(customer == null) return ResponseEntity.notFound().build();
        Customer updatedCustomer = customerMapper.toCustomer(updCustomer);
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Long id) {
        Customer customer = getCustomerById(id);
        if(customer == null) return ResponseEntity.notFound().build();
        customerList.remove(customer);
        return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
    }
    
}
