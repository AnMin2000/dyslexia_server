package com.codingrecipe.project01.controller;

import com.codingrecipe.project01.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

@RestController
public class ImageController {
    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage() throws IOException {
        String imagePath = "C:\\Users\\user\\Desktop\\Album\\JPEG_20230913_213712_5601104748635264103.jpg";
        File imageFile = new File(imagePath);

        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}

