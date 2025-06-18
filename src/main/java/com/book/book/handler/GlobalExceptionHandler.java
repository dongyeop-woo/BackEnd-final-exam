package com.book.book.handler;

import com.book.book.exception.LoginFailException;
import com.book.book.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 로그인 실패 및 유저 조회 실패 에러
    @ExceptionHandler({LoginFailException.class, UserNotFoundException.class})
    public ResponseEntity<?> handleApiException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // View Error 렌더링
    @ExceptionHandler(RuntimeException.class)
    public String handleViewException(RuntimeException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
