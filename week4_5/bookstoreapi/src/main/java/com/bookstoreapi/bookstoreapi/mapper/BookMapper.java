package com.bookstoreapi.bookstoreapi.mapper;

import org.mapstruct.Mapper;

import com.bookstoreapi.bookstoreapi.DTOs.BookDTO;
import com.bookstoreapi.bookstoreapi.entities.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toBookDTO(Book book);
    Book toBook(BookDTO bookdto);
}
