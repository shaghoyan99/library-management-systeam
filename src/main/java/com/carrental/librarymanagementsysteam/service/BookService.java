package com.carrental.librarymanagementsysteam.service;

import com.carrental.librarymanagementsysteam.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllByCategoryId(Integer categoryId);

    List<Book> findAllByTitleContainingIgnoreCase(String search);

    List<Book> findAll();

    Book findById(Integer id);

    void save(Book book);

    void deleteById(Integer id);
}
