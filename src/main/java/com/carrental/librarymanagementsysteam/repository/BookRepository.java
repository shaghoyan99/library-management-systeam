package com.carrental.librarymanagementsysteam.repository;

import com.carrental.librarymanagementsysteam.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByCategoryId(int categoryId);
}
