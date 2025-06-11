package com.book.book.controller;

import com.book.book.entity.User;
import com.book.book.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            User user = userServiceImpl.login(username, password);
            session.setAttribute("user", user);
            return "redirect:/";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, RedirectAttributes redirectAttributes) {
        try {
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();
            userServiceImpl.register(user);
            redirectAttributes.addFlashAttribute("success", "회원가입이 완료");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}