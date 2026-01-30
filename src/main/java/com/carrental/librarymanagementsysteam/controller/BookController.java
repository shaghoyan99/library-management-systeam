package com.carrental.librarymanagementsysteam.controller;

import com.carrental.librarymanagementsysteam.model.Book;
import com.carrental.librarymanagementsysteam.repository.BookRepository;
import com.carrental.librarymanagementsysteam.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "book";
    }

    @GetMapping("/books/filter")
    public String filterBooks(@RequestParam("categories") int categoryId, Model model) {
        model.addAttribute("books", bookRepository.findAllByCategoryId(categoryId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "book";
    }

    @GetMapping("/books/add")
    public String addBook(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "add-book";
    }

    @PostMapping("/books/add")
    public String addBookPost(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
