package com.carrental.librarymanagementsysteam.service.imple;

import com.carrental.librarymanagementsysteam.model.Category;
import com.carrental.librarymanagementsysteam.repository.CategoryRepository;
import com.carrental.librarymanagementsysteam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
