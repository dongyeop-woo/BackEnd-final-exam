package com.book.book.service;

import com.book.book.dto.UserRegisterRequestDTO;
import com.book.book.entity.Book;
import com.book.book.entity.User;
import java.util.List;

public interface UserService {
    User register(UserRegisterRequestDTO userRegisterRequestDTO);
    User login(String username, String password);
    List<Book> getFavorites(String username);
    void toggleFavorite(String username, Long bookId);
}
