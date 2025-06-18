package com.book.book.exception;

public class UserAlreadyException extends RuntimeException {
    public UserAlreadyException() {
        super("이미 계정이 있습니다.");
    }
}
