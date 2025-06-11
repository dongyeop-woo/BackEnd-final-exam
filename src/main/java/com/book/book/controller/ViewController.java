package com.book.book.controller;

import com.book.book.entity.Book;
import com.book.book.service.StoreService;
import com.book.book.enums.BookBest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final StoreService storeService;

    @GetMapping("/main")
    public String mainPage(Model model) {
        List<Book> allBooks = storeService.findAll();

        List<String> categories = allBooks.stream()
                .map(Book::getCategory)
                .distinct()
                .collect(Collectors.toList());
        
        List<Book> bestsellerBooks = allBooks.stream()
                .filter(book -> book.getBookBest() == BookBest.YES)
                .collect(Collectors.toList());
        
        model.addAttribute("books", allBooks);
        model.addAttribute("bestsellerBooks", bestsellerBooks);
        model.addAttribute("categories", categories);
        return "MainPage";
    }

    @GetMapping("/")
    public String redirectToMain() {
        return "redirect:/main";
    }

    @GetMapping("/books/bestseller")
    public String bestsellerPage(Model model) {
        List<Book> allBooks = storeService.findAll();
        List<Book> bestsellerBooks = allBooks.stream()
                .filter(book -> book.getBookBest() == BookBest.YES)
                .collect(Collectors.toList());
        
        model.addAttribute("books", bestsellerBooks);
        return "BestsellerPage";
    }

    @GetMapping("/books/category")
    public String categoryListPage(Model model) {
        List<Book> allBooks = storeService.findAll();
        List<String> categories = allBooks.stream()
                .map(Book::getCategory)
                .distinct()
                .collect(Collectors.toList());
        
        model.addAttribute("categories", categories);
        model.addAttribute("books", allBooks);
        return "CategoryPage";
    }

    @GetMapping("/books/category/{category}")
    public String categoryPage(@PathVariable String category, Model model) {
        List<Book> allBooks = storeService.findAll();
        List<String> categories = allBooks.stream()
                .map(Book::getCategory)
                .distinct()
                .collect(Collectors.toList());
        
        List<Book> categoryBooks = allBooks.stream()
                .filter(book -> category.equals(book.getCategory()))
                .collect(Collectors.toList());
        
        model.addAttribute("categories", categories);
        model.addAttribute("books", categoryBooks);
        model.addAttribute("category", category);
        return "CategoryPage";
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam(required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            List<Book> allBooks = storeService.findAll();
            List<Book> searchResults = allBooks.stream()
                    .filter(book -> book.getName().contains(keyword) || 
                                  book.getAuthor().contains(keyword) ||
                                  book.getCategory().contains(keyword))
                    .collect(Collectors.toList());
            model.addAttribute("books", searchResults);
            model.addAttribute("keyword", keyword);
        }
        return "SearchPage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "LoginPage";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "RegisterPage";
    }

    @GetMapping("/books/{id}")
    public String bookDetail(@PathVariable Long id, Model model) {
        return "BookDetailPage";
    }
}
