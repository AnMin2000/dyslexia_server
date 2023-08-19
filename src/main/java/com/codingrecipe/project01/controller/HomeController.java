package com.codingrecipe.project01.controller;

import com.codingrecipe.project01.dto.UserDTO;
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
    public String androidResponse(@RequestBody UserDTO user) {

        System.out.println("Connection from Android");

        return "12";
    }

    @ResponseBody
    @PostMapping(value="/insert")
    public String insert(@RequestBody UserDTO user){
        System.out.println("id1: " + user.getId() + ", pw: " + user.getPassword());
        homeService.insert(user);
        //System.out.println("id: " + user.getId() + ", pw: " + user.getPassword());
        return "insert";
    }

}