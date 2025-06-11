package com.book.book.controller;

import com.book.book.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final StoreService storeService;

    @GetMapping("/")
    public String redirectToMain() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        addCommonAttributes(model);
        model.addAttribute("books", storeService.findAll());
        model.addAttribute("bestsellerBooks", storeService.findBestsellerBooks());
        return "MainPage";
    }

    @GetMapping("/books/bestseller")
    public String bestsellerPage(Model model) {
        addCommonAttributes(model);
        model.addAttribute("books", storeService.findBestsellerBooks());
        return "BestsellerPage";
    }

    @GetMapping("/books/category")
    public String categoryListPage(Model model) {
        addCommonAttributes(model);
        model.addAttribute("books", storeService.findAll());
        return "CategoryPage";
    }

    @GetMapping("/books/category/{category}")
    public String categoryPage(@PathVariable String category, Model model) {
        addCommonAttributes(model);
        model.addAttribute("books", storeService.findBooksByCategory(category));
        model.addAttribute("category", category);
        return "CategoryPage";
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam(required = false) String keyword, Model model) {
        addCommonAttributes(model);
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("books", storeService.searchBooks(keyword));
            model.addAttribute("keyword", keyword);
        }
        return "SearchPage";
    }

    @GetMapping("/books/{id}")
    public String bookDetail(@PathVariable Long id, Model model) {
        addCommonAttributes(model);
        return "BookDetailPage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "LoginPage";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "RegisterPage";
    }

    private void addCommonAttributes(Model model) {
        model.addAttribute("categories", storeService.findAllCategories());
    }
}
