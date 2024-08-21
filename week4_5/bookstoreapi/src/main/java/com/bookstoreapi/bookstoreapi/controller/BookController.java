package com.bookstoreapi.bookstoreapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapi.bookstoreapi.DTOs.BookDTO;
import com.bookstoreapi.bookstoreapi.entities.Book;
import com.bookstoreapi.bookstoreapi.exceptions.NoSuchBookExist;
import com.bookstoreapi.bookstoreapi.mapper.BookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> booklist = new ArrayList<Book>();

    @Autowired 
    private BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBook() {
        return ResponseEntity.ok().body(booklist.stream().map(bookMapper::toBookDTO).collect(Collectors.toList()));
    }

    @GetMapping("/query")
    public ResponseEntity<BookDTO> getBookByTitleAndAuthor(@RequestParam String title, @RequestParam String author){
        Book book = booklist.stream().filter(data -> data.getTitle().equals(title) && data.getAuthor().equals(author)).findFirst().orElse(null);
        return (book == null)? ResponseEntity.notFound().build() : ResponseEntity.ok().body(bookMapper.toBookDTO(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = booklist.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
        if(book == null) throw new NoSuchBookExist("No Such Book Exists");
        return ResponseEntity.ok().body(bookMapper.toBookDTO(book));
    }

    private Book getBook(Long id) {
        return booklist.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
    }


    @PostMapping
    public ResponseEntity<BookDTO> postBook(@RequestBody BookDTO entity) {
        booklist.add(bookMapper.toBook(entity));
        return ResponseEntity.created(null).body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> putBook(@PathVariable Long id, @RequestBody BookDTO entity) {
        Book book = getBook(id);
        if(book == null) return ResponseEntity.notFound().build();
        Book updatedBook = bookMapper.toBook(entity);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setIsbn(updatedBook.getIsbn());
        return ResponseEntity.ok().body(bookMapper.toBookDTO(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        Book book = getBook(id);
        if(book == null) return ResponseEntity.notFound().build();
        booklist.remove(book);
        return ResponseEntity.ok().body(bookMapper.toBookDTO(book));
    }
}
