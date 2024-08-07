package com.library.service;

import com.library.repository.BookRepository;
// Q: Create a package com.library.service and add a class BookService.
public class BookService {
    private BookRepository repository;
    //Q: Ensure that BookService class has a setter method for BookRepository.
    public void setBookRepository(BookRepository repository) {
        this.repository = repository;
    }

    public void checkSetup() {
        this.repository.display();
    }    
}
