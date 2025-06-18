package com.book.book.repository;

import com.book.book.entity.Book;
import com.book.book.enums.BookBest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookBest(BookBest bookBest);
    List<Book> findByCategory(String category);

    // Data JPA에서 인식 못하는 쿼리문을 직접 넣어 서비스 로직 최소화
    @Query("SELECT b FROM Book b WHERE b.name LIKE %:keyword% OR b.author LIKE %:keyword%")
    List<Book> searchBooksByKeyword(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT b.category FROM Book b")
    List<String> findDistinctCategories();
}
