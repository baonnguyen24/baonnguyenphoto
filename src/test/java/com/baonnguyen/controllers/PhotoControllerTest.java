package com.baonnguyen.controllers;

import com.baonnguyen.services.CategoryService;
import com.baonnguyen.services.PhotoService;
import com.baonnguyen.services.impl.PhotoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PhotoController.class)
public class PhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhotoServiceImpl photoService;

    @MockBean
    private CategoryService catergoryService;

    @Test
    void testGetPhotoPage() throws Exception{
        when(photoService.findAllPhoto()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("photos"));
    }
}
