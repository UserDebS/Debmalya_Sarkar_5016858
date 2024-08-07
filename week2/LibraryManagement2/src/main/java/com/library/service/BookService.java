package com.library.service;

import org.springframework.stereotype.Service;

import com.library.repository.BookRepository;

// Q: Use @Service annotation for the BookService class.
@Service
public class BookService {
    private BookRepository repository;

    
    public BookService(BookRepository repository) {//For constructor injection
        this.repository = repository;
    }

    // Q: Ensure that the BookService class has a setter method for BookRepository.
    public void setBookRepository(BookRepository repository) {
        this.repository = repository;
    }

    public void accessRepo() {
        this.repository.display();
    }
}
