package com.baonnguyen.controllers;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.services.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@Controller
public class PhotoController {

    private PhotoService photoService;
    private static final Logger logger = LogManager.getLogger(PhotoController.class);

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
    public String displayPhotoCollection(@RequestParam(value = "galleryName", required = false) String galleryName , Model model) {
        List<PhotoDto> photos;
        if(galleryName == null || galleryName.isEmpty()) {
            photos = photoService.getPhotoByGallery("landscape");
        } else{
            photos = photoService.getPhotoByGallery(galleryName);
        }
        model.addAttribute("photos", photos);
        return "admin";
    }

    @PostMapping("/admin/uploadPhoto")
    public String uploadPhoto(@RequestParam("photoFile") MultipartFile file,
                              @RequestParam("galleryName") String galleryName,
                              Model model){
        logger.info("Upload photo method called");
        // Check file is empty
        if(file.isEmpty()){
            model.addAttribute("message", "Please select a file");
            return "redirect:/admin";
        }

        // Check galleryName must be selected
        if(galleryName == null || galleryName.trim().isEmpty()){
            model.addAttribute("error", "Please select a gallery before uploading");
            return "admin";
        }

        try {
            photoService.handleUploadPhoto(file, galleryName);
            model.addAttribute("message", "Photo uploaded successfully");
            return "redirect:/admin";
        } catch (Exception e) {
            model.addAttribute("error", "Error uploading photo: " + e.getMessage());
            logger.error("Error uploading photo", e.getMessage());
            return "admin";
        }
    }

    @GetMapping("/photo/{id}")
    public String deletePhoto(@PathVariable("id") Long id) {
        logger.info("Delete photo");
        try{
            photoService.deletePhotoById(id);
            return "redirect:/admin";
        } catch (Exception e){
            logger.error("Failed to delete photo", e);
            return "error";
        }
    }
}
