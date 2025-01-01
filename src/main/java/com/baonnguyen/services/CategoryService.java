package com.baonnguyen.services;

import com.baonnguyen.dto.CategoryDto;
import com.baonnguyen.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findByCatName(String catName);
    Category createCategory(String catName);
    List<CategoryDto> findAllCategories();
}
