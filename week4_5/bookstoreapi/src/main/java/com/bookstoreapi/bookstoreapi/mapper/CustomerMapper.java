package com.bookstoreapi.bookstoreapi.mapper;

import org.mapstruct.Mapper;

import com.bookstoreapi.bookstoreapi.DTOs.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDTO customerDTO);
    CustomerDTO toCustomerDTO(Customer customer);
}
