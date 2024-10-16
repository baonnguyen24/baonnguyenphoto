package com.baonnguyen.controllers;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.models.Photo;
import com.baonnguyen.services.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        List<PhotoDto> photos = photoService.findAllPhoto();
        model.addAttribute("photos", photos);
        return "index";
    }

    @GetMapping("/admin")
    public String displayPhotoCollection(Model model) {
        // NEED TO IMPLEMENT THE FILTER TO SELECT COLLECTION
        List<PhotoDto> photos = photoService.findAllPhoto();
        model.addAttribute("photos", photos);
        return "admin";
    }

    // ==== GETMAPPING METHOD TO NAVIGATE AROUND
    @GetMapping("/landscape")
    public String loadLandscapePhoto(Model model) {
        List<PhotoDto> photos = photoService.findAllPhoto();
        model.addAttribute("photos", photos);
        return "gallery-landscape";
    }

    @GetMapping("/cityscape")
    public String loadCityscapePhoto(Model model) {
        List<PhotoDto> photos = photoService.findAllPhoto();
        model.addAttribute("photos", photos);
        return "gallery-cityscape";
    }

    @GetMapping("/event")
    public String loadEventPhoto(Model model) {
        List<PhotoDto> photos = photoService.findAllPhoto();
        model.addAttribute("photos", photos);
        return "gallery-event";
    }

    @GetMapping("/product")
    public String loadProductPhoto(Model model) {
        List<PhotoDto> photos = photoService.findAllPhoto();
        model.addAttribute("photos", photos);
        return "gallery-product";
    }
}
