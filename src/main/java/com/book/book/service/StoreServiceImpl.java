package com.book.book.service;

import com.book.book.entity.Book;
import com.book.book.repository.StoreRepository;
import com.book.book.enums.BookBest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return storeRepository.findAll();
    }

    @Override
    @Transactional
    public Book setBook(Book book) {
        return storeRepository.save(
                Book.builder()
                        .name(book.getName())
                        .author(book.getAuthor())
                        .category(book.getCategory())
                        .bookBest(book.getBookBest())
                        .build()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBestsellerBooks() {
        return findAll().stream()
                .filter(book -> book.getBookBest() == BookBest.YES)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllCategories() {
        return findAll().stream()
                .map(Book::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByCategory(String category) {
        return findAll().stream()
                .filter(book -> category.equals(book.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }
        
        return findAll().stream()
                .filter(book -> book.getName().contains(keyword) || 
                              book.getAuthor().contains(keyword) ||
                              book.getCategory().contains(keyword))
                .collect(Collectors.toList());
    }
}
