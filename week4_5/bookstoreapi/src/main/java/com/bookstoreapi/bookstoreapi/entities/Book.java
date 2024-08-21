package com.bookstoreapi.bookstoreapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private static Long idGen = (long)(0);
    private Long id = ++idGen;
    private String title, author, isbn;
    private double price;
}
