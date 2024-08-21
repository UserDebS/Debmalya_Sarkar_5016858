package com.bookstoreapi.bookstoreapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapi.bookstoreapi.entities.Book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> booklist = new ArrayList<Book>();

    @GetMapping
    public ResponseEntity<List<Book>> getBook() {
        return ResponseEntity.ok().body(booklist);
    }

    @GetMapping("/query")
    public ResponseEntity<Book> getBookByTitleAndAuthor(@RequestParam String title, @RequestParam String author){
        Book book = booklist.stream().filter(data -> data.getTitle().equals(title) && data.getAuthor().equals(author)).findFirst().orElse(null);
        return (book == null)? ResponseEntity.notFound().build() : ResponseEntity.ok().body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = booklist.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
        return (book == null)? ResponseEntity.notFound().build() : ResponseEntity.ok().body(book);
    }

    private Book getBook(Long id) {
        return booklist.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
    }
    

    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book entity) {
        booklist.add(entity);
        return ResponseEntity.ok().body(entity);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> putBook(@PathVariable Long id, @RequestBody Book entity) {
        Book book = getBook(id);
        if(book == null) return ResponseEntity.notFound().build();
        book.setTitle(entity.getTitle());
        book.setAuthor(entity.getAuthor());
        book.setPrice(entity.getPrice());
        book.setIsbn(entity.getIsbn());
        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Book book = getBook(id);
        if(book == null) return ResponseEntity.notFound().build();
        booklist.remove(book);
        return ResponseEntity.ok().body(book);
    }
}
