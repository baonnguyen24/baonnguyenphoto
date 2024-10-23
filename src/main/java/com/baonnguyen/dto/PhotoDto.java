package com.baonnguyen.dto;

import com.baonnguyen.models.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PhotoDto {
    private int id;
    private String title;
    private String description;
    private LocalDateTime uploadedOn;
    private String photoUrl;
    private String category;
}
