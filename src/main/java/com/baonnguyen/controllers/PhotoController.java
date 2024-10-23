package com.baonnguyen.controllers;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.models.Photo;
import com.baonnguyen.services.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PhotoController {
    private PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index"; // Redirect to /index
    }

    @GetMapping("/index")
    public String listPhoto(Model model) {
        List<PhotoDto> photos = photoService.findRandomPhotos(12);
        model.addAttribute("photos", photos);
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) { return "about"; }

    @GetMapping("/{galleryName}")
    public String loadGalleryPhoto(@PathVariable("galleryName") String galleryName, Model model) {
        List<PhotoDto> photos = photoService.getPhotoByGallery(galleryName);
        model.addAttribute("photos", photos);
        return "gallery-"+galleryName;
    }

    @GetMapping("/admin")
    public String displayPhotoCollection(Model model) {
        // NEED TO IMPLEMENT THE FILTER TO SELECT COLLECTION
        List<PhotoDto> photos = photoService.findAllPhoto();
        model.addAttribute("photos", photos);
        return "admin";
    }
}
