package com.baonnguyen.services.impl;

import com.baonnguyen.dto.CategoryDto;
import com.baonnguyen.models.Category;
import com.baonnguyen.repository.CategoryRepository;
import com.baonnguyen.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // ===== CONSTRUCTOR ======
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // ========================
    // ========================
    // ===== METHODS ==========

    @Override
    public List<CategoryDto> findAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> maptoCategoryDto(category)).collect(Collectors.toList());
    }

    private CategoryDto maptoCategoryDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .catName(category.getCatName())
                .build();
    }

    @Override
    public Optional<Category> findByCatName(String catName){
        return categoryRepository.findByCatName(catName);
    }

    @Override
    public Category createCategory(String catName){
        try{
            Category newCategory = new Category();
            newCategory.setCatName(catName);
            return categoryRepository.save(newCategory);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create category: " + catName, e);
        }
    }
}
