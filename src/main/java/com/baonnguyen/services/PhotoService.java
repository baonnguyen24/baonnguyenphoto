package com.baonnguyen.services;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.models.Photo;

import java.util.List;

public interface PhotoService {
    List<PhotoDto> findAllPhoto();

    Photo savePhoto(Photo photo);
}
