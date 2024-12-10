package com.baonnguyen.repository;

import com.baonnguyen.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findByTitle(String url);
    Optional<Photo> findByPhotoUrl(String photoUrl);
    List<Photo> findByCategory_CatName(String galleryName);

    @Query(value = "SELECT * FROM photos ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<Photo> findRandomPhotos(@Param("count") int count);
}
