package com.carrental.librarymanagementsysteam.controller;

import com.carrental.librarymanagementsysteam.model.Category;
import com.carrental.librarymanagementsysteam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "category";
    }

    @GetMapping("/categories/add")
    public String addCategory() {
        return "add-category";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }


}
