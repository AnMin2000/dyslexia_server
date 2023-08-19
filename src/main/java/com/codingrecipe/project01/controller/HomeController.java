package com.codingrecipe.project01.controller;

import com.codingrecipe.project01.dto.Camera;
import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/shot")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        String result = homeService.uploadImage(file);
        if (result.contains("성공")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}