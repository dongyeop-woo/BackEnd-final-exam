package com.book.book.service;

import com.book.book.entity.Book;

import java.util.List;

public interface StoreService {
    List<Book> findAll();
    Book setBook(Book book);
}
