package com.baonnguyen.controllers;

import com.baonnguyen.dto.PhotoDto;
import com.baonnguyen.models.Category;
import com.baonnguyen.models.Photo;
import com.baonnguyen.services.CategoryService;
import com.baonnguyen.services.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PhotoController {
    private PhotoService photoService;
    private CategoryService categoryService;

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
        // NEED TO IMPLEMENT THE FILTER TO SELECT COLLECTION
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
    public String uploadPhoto(@RequestParam("photo") MultipartFile file,
                              @RequestParam("galleryName") String galleryName,
                              Model model){

        if(file.isEmpty()){
            model.addAttribute("message", "Please select a file");
            return "redirect:/admin";
        }

        try{
            // Generate Unique filename with category prefix
            String fileName = galleryName + "_" + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // Define upload gallery -- NEED TO IMPLEMENT ticket BNN-9 here
            String uploadDir = "/src/main/resources/static/uploads/" + galleryName + "/";
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            // Save file to local
            Path path = uploadPath.resolve(fileName); // .resolve will get the full path
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Create and save photo record
            Photo photo = new Photo();
            photo.setTitle(fileName);
            photo.setDescription(null);
            photo.setPhotoUrl("/uploads/" + galleryName + "/" + fileName);
            photo.getUploadedOn();

            // Save Category if not existed
            Category category = categoryService.findByCatName(galleryName)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            photo.setCategory(category);

            // Save to DB
            photoService.savePhoto(photo);

            model.addAttribute("message", "Photo uploaded");
            return "redirect:/admin";
        }catch(IOException e) {
            model.addAttribute("message", "Something went wrong");
            return "error";
        }

    }
}
