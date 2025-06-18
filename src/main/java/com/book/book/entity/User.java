package com.book.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder (toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    private String password;
    
    @Column(unique = true)
    private String email;
    
    private String firstName;
    private String lastName;
} 