package com.baonnguyen.services;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.models.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PhotoService {
    List<PhotoDto> findAllPhoto();

    void savePhoto(Photo photo);

    public List<PhotoDto> getPhotoByGallery(String galleryName);

    public List<PhotoDto> findRandomPhotos(int count);

    void deletePhotoById(Long id);

    boolean isDuplicatedPhoto(String title, String photoUrl);

    void handleUploadPhoto(MultipartFile file, String galleryName) throws IOException;
}
