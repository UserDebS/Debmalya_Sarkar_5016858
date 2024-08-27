package com.bookstoreapi.bookstoreapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapi.bookstoreapi.DTOs.BookDTO;
import com.bookstoreapi.bookstoreapi.entities.Book;
import com.bookstoreapi.bookstoreapi.exceptions.NoSuchBookExist;
import com.bookstoreapi.bookstoreapi.mapper.BookMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(
        summary = "Get books",
        description = "Get all the books available",
        responses = {
            @ApiResponse(responseCode = "200", description = "Got the books")
        }
    )
    public ResponseEntity<List<EntityModel<BookDTO>>> getBooks() {
        return ResponseEntity.ok().body(booklist.stream().map(book -> {
            BookDTO bookDTO = bookMapper.toBookDTO(book);
            EntityModel<BookDTO> model = EntityModel.of(bookDTO);
            Link selflink = WebMvcLinkBuilder.linkTo(BookController.class).slash(book.getId()).withSelfRel();
            model.add(selflink);
            return model;
        }).collect(Collectors.toList()));
    }

    @GetMapping(value = "/query", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(
        summary = "Get book",
        description = "Get a book by using its id",
        responses = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "400", description = "Book not found")
        }
    )
    public ResponseEntity<BookDTO> getBookByTitleAndAuthor(@RequestParam String title, @RequestParam String author){
        Book book = booklist.stream().filter(data -> data.getTitle().equals(title) && data.getAuthor().equals(author)).findFirst().orElse(null);
        return (book == null)? ResponseEntity.notFound().build() : ResponseEntity.ok().body(bookMapper.toBookDTO(book));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = booklist.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
        if(book == null) throw new NoSuchBookExist("No Such Book Exists");
        return ResponseEntity.ok().body(bookMapper.toBookDTO(book));
    }

    private Book getBook(Long id) {
        return booklist.stream().filter(data -> data.getId().equals(id)).findFirst().orElse(null);
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> postBook(@RequestBody BookDTO entity) {
        booklist.add(bookMapper.toBook(entity));
        return ResponseEntity.created(null).body(entity);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        Book book = getBook(id);
        if(book == null) return ResponseEntity.notFound().build();
        booklist.remove(book);
        return ResponseEntity.ok().body(bookMapper.toBookDTO(book));
    }
}
