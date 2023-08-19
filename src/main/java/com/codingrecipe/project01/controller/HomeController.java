package com.codingrecipe.project01.controller;

import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {
    //[GET : SELECT], [POST : INSERT], [PUT : UPDATE], [DELETE : DELETE]

    private final HomeService homeService;


    @ResponseBody
    @PostMapping(value="/insert")
    public String insert(@RequestBody User user){
        System.out.println("id1: " + user.getId() + ", pw: " + user.getPassword());
        homeService.insert(user);
        return "insert";
    }

    @ResponseBody
    @PostMapping(value="/shot")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        System.out.println(file);
        homeService.uploadImage(file);
        return "shot";
    }
}