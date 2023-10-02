package com.codingrecipe.project01.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class PhotoController {

    @GetMapping("/getPhotos")
    public ResponseEntity<List<String>> getPhotos() throws IOException {
        List<String> photoList = new ArrayList<>();

        // 이미지 파일들의 경로를 배열에 추가
        String[] imagePaths = {
                "C:/Users/user/Desktop/Album/JPEG_20230911.jpg",
                "C:/Users/user/Desktop/Album/JPEG_20230911.jpg"
                // 다른 이미지 경로도 추가 가능
        };

        for (String imagePath : imagePaths) {
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
            photoList.add(encodedImage);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(photoList);
    }
}
