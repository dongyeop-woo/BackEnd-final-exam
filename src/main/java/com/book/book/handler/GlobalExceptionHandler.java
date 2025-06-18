package com.book.book.handler;

import com.book.book.exception.LoginFailException;
import com.book.book.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({LoginFailException.class, UserNotFoundException.class})
    public ResponseEntity<?> handleApiException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleViewException(RuntimeException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
