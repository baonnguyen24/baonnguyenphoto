package com.baonnguyen.repository;

import com.baonnguyen.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCatName(String name);
}
