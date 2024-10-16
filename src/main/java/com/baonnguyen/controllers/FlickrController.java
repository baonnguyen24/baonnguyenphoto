package com.baonnguyen.controllers;

import com.baonnguyen.services.FlickrAuthService;
import com.baonnguyen.services.FlickrService;
import com.baonnguyen.services.impl.FlickrAuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/api/flickr")
public class FlickrController {

    private FlickrService flickerService;
    private FlickrAuthServiceImpl flickrAuthService;

    // ==== USER AUTHENTICATION ====
    // Redirect to Flickr for user authentication
    @GetMapping("/auth")
    public ResponseEntity<String> authorizedFlickr() throws Exception{
        String callbackUrl = "http://yourapp.com/api/flickr/callback";
        String authUrl = flickrAuthService.getRequestToken(callbackUrl);

        // Redirect user to Flickr
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("location", authUrl)
                .build();
    }

    // Handle OAuth callback and exchange verifier for Access Token
    @GetMapping("/callback")
    public String handleFlickerCallback(@RequestParam String oauth_verifier) throws Exception {
        // Exchange verifier for an access token
        flickrAuthService.getAccessToken(oauth_verifier);

        // Return success message (or redirect user)
        return "redirect:/admin.html";
    }

    // ==== ACTION ====
    @DeleteMapping("/delete")
    public String deletePhoto(@RequestParam String photoId){
        return flickerService.deletePhoto(photoId);
    }
}
