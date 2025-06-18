package com.book.book.controller;

import com.book.book.dto.UserLoginRequestDTO;
import com.book.book.dto.UserRegisterRequestDTO;
import com.book.book.entity.User;
import com.book.book.exception.LoginNeedException;
import com.book.book.exception.UserAlreadyException;
import com.book.book.mapper.UserMapper;
import com.book.book.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO request, @ModelAttribute HttpSession session) {
        User user = userService.login(request.getUsername(), request.getPassword());
        session.setAttribute("user", user);
        return ResponseEntity.ok().build();
    }

    // 회원가입 및 중복 검증
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequestDTO requestDTO) {
        try {
            User savedUser = userService.register(requestDTO);
            UserRegisterRequestDTO request = userMapper.toDTO(savedUser);
            return ResponseEntity.ok(request);
         }catch (DataIntegrityViolationException e) {
            throw new UserAlreadyException();
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    // 책 목록 전달
    @PostMapping("/books/{id}/favorite")
    @ResponseBody
    public ResponseEntity<?> toggleFavorite(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new LoginNeedException();
        }
        userService.toggleFavorite(userDetails.getUsername(), id);
        return ResponseEntity.ok().build();
    }
}