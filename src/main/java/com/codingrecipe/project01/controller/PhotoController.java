package com.codingrecipe.project01.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@RestController
public class PhotoController {

    private final String[] imagePaths = {
            "C:/Users/user/Desktop/Album/JPEG_20230911.jpg",
            "C:/Users/user/Desktop/Album/JPEG_20230911.jpg",
            "C:/Users/user/Desktop/Album/JPEG_20230911.jpg",
            "C:/Users/user/Desktop/Album/JPEG_20230911.jpg",
            "C:/Users/user/Desktop/Album/JPEG_20230911.jpg",
            "C:/Users/user/Desktop/Album/JPEG_20230911.jpg",
            "C:/Users/user/Desktop/Album/JPEG_20230911.jpg",
            "C:/Users/user/Desktop/Album/JPEG_20230911.jpg"
            // 다른 이미지 경로도 추가 가능
    };

    @GetMapping("/getPhotoCount")
    public ResponseEntity<Integer> getPhotoCount() {
        int photoCount = imagePaths.length;
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(photoCount);
    }

    @GetMapping("/getPhoto")
    public ResponseEntity<String> getPhoto(@RequestParam("index") int index) throws IOException {
        if (index >= 0 && index < imagePaths.length) {
            String imagePath = imagePaths[index];
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(encodedImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}