package com.book.book.controller;

import com.book.book.exception.UserNotFoundException;
import com.book.book.service.StoreService;
import com.book.book.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final StoreService storeService;
    private final UserService userService;

    // 메인페이지
    @GetMapping("/")
    public String redirectToMain() {
        return "redirect:/main";
    }

    // 타임리프 전달
    @GetMapping("/main")
    public String mainPage(Model model) {
        addCommonAttributes(model);
        model.addAttribute("books", storeService.findAll());
        model.addAttribute("bestsellerBooks", storeService.findBestsellerBooks());
        return "MainPage";
    }

    // 베스트셀러 전달
    @GetMapping("/books/bestseller")
    public String bestsellerPage(Model model) {
        addCommonAttributes(model);
        model.addAttribute("books", storeService.findBestsellerBooks());
        return "BestsellerPage";
    }

    // 카테고리별 전달
    @GetMapping("/books/category")
    public String categoryListPage(Model model) {
        addCommonAttributes(model);
        model.addAttribute("books", storeService.findAll());
        return "CategoryPage";
    }

    // 카테고리별 하나하나 확인 (html에 종목별 버튼)
    @GetMapping("/books/category/{category}")
    public String categoryPage(@PathVariable String category, Model model) {
        addCommonAttributes(model);
        model.addAttribute("books", storeService.findBooksByCategory(category));
        model.addAttribute("category", category);
        return "CategoryPage";
    }

    // 책 검색
    @GetMapping("/books/search")
    public String searchBooks(@RequestParam(required = false) String keyword, Model model) {
        addCommonAttributes(model);
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("books", storeService.searchBooks(keyword));
            model.addAttribute("keyword", keyword);
        }
        return "SearchPage";
    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String loginPage() {
        return "LoginPage";
    }

    // 회원가입 페이지 이동
    @GetMapping("/register")
    public String registerPage() {
        return "RegisterPage";
    }

    // 찜 목록 페이지 이동
    @GetMapping("/favorites")
    public String favoritesPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        // 간단 로직이라 책임분리 안함 그냥 컨트롤러에서 처리
        if (userDetails == null) {
            return "redirect:/login";
        }
        try {
            model.addAttribute("favorites", userService.getFavorites(userDetails.getUsername()));
        } catch (UserNotFoundException e) {
            return "redirect:/login";
        }
        return "bookFavorites";
    }

    // 모든 카테고리 전달
    private void addCommonAttributes(Model model) {
        model.addAttribute("categories", storeService.findAllCategories());
    }
}
