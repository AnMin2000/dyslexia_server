package com.codingrecipe.project01.controller;

import com.codingrecipe.project01.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.List;

@RestController
public class ImageController {

    private ImageService imageService;

    @GetMapping(value = "/getImages/{userID}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Flux<byte[]> getImages(@PathVariable String userID) {
        List<byte[]> images = imageService.getImages(userID);
        return Flux.fromIterable(images);
    }
}
