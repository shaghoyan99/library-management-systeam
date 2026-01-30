package com.carrental.librarymanagementsysteam.repository;

import com.carrental.librarymanagementsysteam.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
