package com.baonnguyen.repository;

import com.baonnguyen.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findByTitle(String url);
}
