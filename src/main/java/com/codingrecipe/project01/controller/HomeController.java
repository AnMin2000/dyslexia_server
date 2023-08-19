package com.codingrecipe.project01.controller;

import com.codingrecipe.project01.dto.Camera;
import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {
    //[GET : SELECT], [POST : INSERT], [PUT : UPDATE], [DELETE : DELETE]

    private final HomeService homeService;
    @ResponseBody
    @GetMapping("/")
    public String index(){
        return "1234";
    }

    @ResponseBody
    @PostMapping(value="/android")
    public String androidResponse(@RequestBody User user) {

        System.out.println("Connection from Android");

        return "12";
    }

    @ResponseBody
    @PostMapping(value="/insert")
    public String insert(@RequestBody User user){
        System.out.println("id1: " + user.getId() + ", pw: " + user.getPassword());
        homeService.insert(user);
        return "insert";
    }

    @ResponseBody
    @PostMapping(value = "/shot")
    public String shot(@RequestBody Camera camera){
        System.out.println("카메라" + camera.getAlbumId());
        homeService.shot(camera);
        return "shot";
    }
}