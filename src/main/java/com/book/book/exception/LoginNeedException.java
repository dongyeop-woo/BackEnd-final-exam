package com.book.book.exception;

public class LoginNeedException extends RuntimeException {
    public LoginNeedException() {
        super("로그인이 필요합니다.");
    }
}
