package com.bookstoreapi.bookstoreapi.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private static Long idGen = (long)(0);
    private Long id = ++idGen;
    private String email;
    @JsonProperty("Cname")
    private String name;
}
