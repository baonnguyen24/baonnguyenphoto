package com.baonnguyen.services;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.models.Category;
import com.baonnguyen.models.Photo;
import com.baonnguyen.repository.PhotoRepository;
import com.baonnguyen.services.impl.PhotoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceTest {

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoServiceImpl photoService;

    @Mock
    private CategoryService categoryService;

    @Test
    void TestFindAllPhotos(){

        // Arrange: setup test data (photos)
        List<Photo> photos = new ArrayList<>();
        Photo photo1 = new Photo();
        photo1.setTitle("Sunset");

        Category category = new Category();
        category.setCatName("Nature");
        photo1.setCategory(category);

        photos.add(photo1);

        // Mock the behavior
        // Since photoRepository is a mock object (created with @Mock), it doesn’t perform actual database operations.
        // Instead, you can define how it should behave when specific methods are called, allowing you to control the output it returns.
        when(photoRepository.findAll()).thenReturn(photos);

        // Act: call the method under test (photoService.findAllPhoto()) and store the result
        List<PhotoDto> foundPhoto = photoService.findAllPhoto();

        // Assert: compare the test data with expectations
        assertEquals(1, foundPhoto.size());
        assertEquals("Sunset", foundPhoto.get(0).getTitle());
        assertEquals("Nature", foundPhoto.get(0).getCategory());
    }
}
