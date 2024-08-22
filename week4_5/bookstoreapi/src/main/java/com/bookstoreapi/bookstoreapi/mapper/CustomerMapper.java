package com.bookstoreapi.bookstoreapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.bookstoreapi.bookstoreapi.DTOs.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entities.Customer;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toCustomer(CustomerDTO customerDTO);
    CustomerDTO toCustomerDTO(Customer customer);
}
