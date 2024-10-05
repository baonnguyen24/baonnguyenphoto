package com.baonnguyen.services.impl;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.models.Photo;
import com.baonnguyen.repository.PhotoRepository;
import com.baonnguyen.services.PhotoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {
    private PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public List<PhotoDto> findAllPhoto() {
        List<Photo> photos = photoRepository.findAll();
        return photos.stream().map((photo) -> mapToPhotoDto(photo)).collect(Collectors.toList());
    }

    private PhotoDto mapToPhotoDto(Photo photo){
        return PhotoDto.builder()
                .id(photo.getId())
                .title(photo.getTitle())
                .description(photo.getDescription())
                .photoUrl(photo.getPhotoUrl())
                .uploadedOn(photo.getUploadedOn())
                .Category(photo.getCategory())
                .build();

    }
}
