package com.baonnguyen.services.impl;

import com.baonnguyen.models.Category;
import com.baonnguyen.repository.CategoryRepository;
import com.baonnguyen.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    // ===== CONSTRUCTOR ======
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // ========================
    // ========================
    // ===== METHODS ==========
    public Optional<Category> findByCatName(String catName){
        return categoryRepository.findByCatName(catName);
    }
}
