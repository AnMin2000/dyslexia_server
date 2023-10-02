package com.codingrecipe.project01.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class PhotoController {

    @GetMapping("/getPhoto")
    public ResponseEntity<Resource> getPhoto() throws MalformedURLException {
        // 파일 경로 설정
        Path filePath = Paths.get("C:/Users/user/Desktop/Album/JPEG_20230911.jpg");
        Resource resource = new UrlResource(filePath.toUri());

        // 파일을 불러와서 HTTP 응답으로 전송
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=JPEG_20230911.jpg");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
