package com.book.book.controller;

import com.book.book.dto.BookDTO;
import com.book.book.entity.Book;
import com.book.book.mapper.BookMapper;
import com.book.book.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;
    private final BookMapper bookMapper;

    // 책 생성
    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {
        Book newBook = storeService.setBook(bookDTO);
        BookDTO responseDTO = bookMapper.toDTO(newBook);
        return ResponseEntity.ok(responseDTO);
    }

    // 책 조회
    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(storeService.findAll());
    }
}
