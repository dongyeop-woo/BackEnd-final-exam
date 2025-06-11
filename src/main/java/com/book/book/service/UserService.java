package com.book.book.service;

import com.book.book.entity.User;

public interface UserService {
    User register(User user);
    User login(String username, String password);
}
