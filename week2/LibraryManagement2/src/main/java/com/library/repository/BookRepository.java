package com.library.repository;

import org.springframework.stereotype.Repository;

// Q: Use @Repository annotation for the BookRepository class.
@Repository
public class BookRepository {
    public void display() {
        System.out.println("Repository has been accessed");
    }
}
