package com.book.book.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Builder (toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    // ID 대체용
    // 딱히 필드명이 생각안나 username 으로 설정
    private String username;
    
    private String password;
    
    @Column(unique = true)
    private String email;
    
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(
        name = "user_favorites",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> favorites;

    public void setFavorites(Set<Book> favorites) {
        this.favorites = favorites;
    }
} 