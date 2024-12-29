package com.baonnguyen.services;

import com.baonnguyen.models.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findByCatName(String catName);
    Category createCategory(String catName);
}
