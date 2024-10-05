package com.baonnguyen.services;

import com.baonnguyen.dto.PhotoDto;

import java.util.List;

public interface PhotoService {
    List<PhotoDto> findAllPhoto();
}
