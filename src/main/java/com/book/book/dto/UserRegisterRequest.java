package com.book.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegisterRequest {

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private String firstName;

    private String lastName;

    @Builder
    public UserRegisterRequest(String username, String password, String confirmPassword, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
