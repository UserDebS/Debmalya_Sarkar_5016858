package com.bookstoreapi.bookstoreapi.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bookstoreapi.bookstoreapi.DTOs.BookDTO;
import com.bookstoreapi.bookstoreapi.entities.Book;
import com.bookstoreapi.bookstoreapi.mapper.BookMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookMapper bookMapper;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetBookById() throws Exception {
        Long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);
        bookDTO.setTitle("Test1");
        bookDTO.setAuthor("Author1");
        bookDTO.setIsbn("12154");
        bookDTO.setPrice(1000);

        Book book = bookMapper.toBook(bookDTO);

        when(bookMapper.toBookDTO(book)).thenReturn(bookDTO);

        mockMvc.perform(post("/books")
        .contentType(MediaType.APPLICATION_JSON)
        .content(bookDTO.toString()));

        mockMvc.perform(get("/books/{id}", bookId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.Btitle").value("Test1"))
        .andExpect(jsonPath("$.author").value("Author1"))
        .andExpect(jsonPath("$.Isbn").value("12154"))
        .andExpect(jsonPath("price").value(1000));
    }
}
