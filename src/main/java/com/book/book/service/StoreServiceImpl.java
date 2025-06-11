package com.book.book.service;

import com.book.book.entity.Book;
import com.book.book.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional
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
}
