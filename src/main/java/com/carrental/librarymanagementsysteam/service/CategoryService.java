package com.carrental.librarymanagementsysteam.service;

import com.carrental.librarymanagementsysteam.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    void save(Category category);

}
