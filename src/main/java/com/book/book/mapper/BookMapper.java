package com.book.book.mapper;

import com.book.book.dto.BookDTO;
import com.book.book.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    public Book toEntity(BookDTO dto) {
        return Book.builder()
                .name(dto.getName())
                .author(dto.getAuthor())
                .category(dto.getCategory())
                .build();
    }

    public BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .category(book.getCategory())
                .build();
    }
}
