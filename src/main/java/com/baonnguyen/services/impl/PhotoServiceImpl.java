package com.baonnguyen.services.impl;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.models.Category;
import com.baonnguyen.models.Photo;
import com.baonnguyen.repository.PhotoRepository;
import com.baonnguyen.services.CategoryService;
import com.baonnguyen.services.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {
    private PhotoRepository photoRepository;
    private CategoryService categoryService;
    private static final Logger logger = LogManager.getLogger(PhotoServiceImpl.class);

    // ===== CONSTRUCTOR ======
    public PhotoServiceImpl(PhotoRepository photoRepository, CategoryService categoryService) {
        this.photoRepository = photoRepository;
        this.categoryService = categoryService;
    }

    // ========================
    // ========================
    // ===== METHODS ==========
    @Override
    public List<PhotoDto> findAllPhoto() {
        List<Photo> photos = photoRepository.findAll();
        return photos.stream().map((photo) -> mapToPhotoDto(photo)).collect(Collectors.toList());
    }

    public List<PhotoDto> findRandomPhotos(int count) {
        List<Photo> photos = photoRepository.findRandomPhotos(count);
        return photos.stream().map((photo) -> mapToPhotoDto(photo)).collect(Collectors.toList());
    }

    private PhotoDto mapToPhotoDto(Photo photo){
        return PhotoDto.builder()
                .id(photo.getId())
                .title(photo.getTitle())
                .description(photo.getDescription())
                .photoUrl(photo.getPhotoUrl())
                .uploadedOn(photo.getUploadedOn())
                .category(photo.getCategory().getCatName())
                .build();
    }

    public void savePhoto(Photo photo){
        try{
            photoRepository.save(photo);
        } catch(Exception e){
            System.err.println("Failed to save photo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<PhotoDto> getPhotoByGallery(String galleryName){
        List<Photo> photos = photoRepository.findByCategory_CatName(galleryName);
        return photos.stream().map((photo) -> mapToPhotoDto(photo)).collect(Collectors.toList());
    }

    @Override
    public void deletePhotoById(Long id){
        try{
            photoRepository.deleteById(id);
        } catch(Exception e) {
            System.err.println("Failed to delete photo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isDuplicatedPhoto(String title, String photoUrl){
        boolean duplicated = photoRepository.findByTitle(title).isPresent() || photoRepository.findByPhotoUrl(photoUrl).isPresent();
        System.out.println("Duplicate check result for " + title + " and URL " + photoUrl + " is " + duplicated);
        return duplicated;
    }

    @Override
    public void handleUploadPhoto(MultipartFile file, String galleryName) throws IOException {
        logger.debug("Handling photo upload for gallery: {}", galleryName);
        try {
            long fileSize = file.getSize();
            System.out.println("uploaded file size: " + fileSize);

            // Generate Unique filename with category prefix
            String fileName = galleryName + "_" + file.getOriginalFilename();

            // Check for duplicate
            if (isDuplicatedPhoto(fileName, "/uploads/" + galleryName + "/" + fileName)) {
                throw new IllegalArgumentException("Photo is already in gallery");
            }

            String uploadDir = System.getProperty("user.dir") + "/uploads/" + galleryName + "/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save file to local
            Path path = uploadPath.resolve(fileName); // .resolve will get the full path
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Create and save photo record
            Photo photo = new Photo();
            photo.setTitle(fileName);
            photo.setDescription(null);
            photo.setPhotoUrl("/uploads/" + galleryName + "/" + fileName);
            photo.setUploadedOn(LocalDateTime.now());

            // Save Category if not existed
            Category category = categoryService.findByCatName(galleryName)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            photo.setCategory(category);

            // Save to DB
            savePhoto(photo);
            logger.info("Photo uploaded successfully");
        } catch (Exception e){
            logger.error("Error uploading photo", e);
            throw new RuntimeException(e);
        }
    }
}
