package com.book.book.service;

import com.book.book.dto.BookDTO;
import com.book.book.entity.Book;

import java.util.List;

public interface StoreService {
    List<Book> findAll();
    Book setBook(BookDTO bookDTO);
    List<Book> findBestsellerBooks();
    List<String> findAllCategories();
    List<Book> findBooksByCategory(String category);
    List<Book> searchBooks(String keyword);
    Book findById(Long id);
}
