package com.book.book.entity;

import com.book.book.enums.BookBest;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String name;
    private String author;
    private String category;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private BookBest bookBest;

    @ManyToMany(mappedBy = "favorites")
    private Set<User> likedBy;
}
