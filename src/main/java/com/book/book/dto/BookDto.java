package com.book.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookDto {
    private String name;
    private String author;
    private String category;

    @Builder
    public BookDto(String name, String author, String category) {
        this.name = name;
        this.author = author;
        this.category = category;
    }
}
