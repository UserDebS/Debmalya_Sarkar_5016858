package com.bookstoreapi.bookstoreapi.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    @NotNull
    @Size(min = 1, max = 100)
    private String title, author, isbn;
    @NotNull
    @Min(0)
    private double price;
}
