package com.carrental.librarymanagementsysteam.controller;

import com.carrental.librarymanagementsysteam.model.Book;
import com.carrental.librarymanagementsysteam.service.BookService;
import com.carrental.librarymanagementsysteam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "book";
    }

    @GetMapping("/books/filter")
    public String filterBooks(@RequestParam("categories") int categoryId, Model model) {
        model.addAttribute("books", bookService.findAllByCategoryId(categoryId));
        model.addAttribute("categories", categoryService.findAll());
        return "book";
    }

    @GetMapping("/books/add")
    public String addBook(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "add-book";
    }

    @PostMapping("/books/add")
    public String addBookPost(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam("search") String search, Model model) {
        model.addAttribute("books", bookService.findAllByTitleContainingIgnoreCase(search));
        return "book";
    }
}
