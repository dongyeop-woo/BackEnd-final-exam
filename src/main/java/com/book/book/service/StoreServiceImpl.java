package com.book.book.service;

import com.book.book.dto.BookDTO;
import com.book.book.entity.Book;
import com.book.book.exception.BookNotFoundException;
import com.book.book.mapper.BookMapper;
import com.book.book.repository.StoreRepository;
import com.book.book.enums.BookBest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return storeRepository.findAll();
    }

    @Override
    @Transactional
    public Book setBook(BookDTO bookDTO) {
        Book newBook = bookMapper.toEntity(bookDTO);
        return storeRepository.save(newBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBestsellerBooks() {
        return storeRepository.findByBookBest(BookBest.YES);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllCategories() {
        return storeRepository.findDistinctCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByCategory(String category) {
        if (category == null || category.isBlank()) return List.of();
        return storeRepository.findByCategory(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.isBlank()) return List.of();
        return storeRepository.searchBooksByKeyword(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return storeRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }
}
