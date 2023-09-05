package com.codingrecipe.project01.controller;

import com.codingrecipe.project01.dto.OcrData;
import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Handles requests for the application home page.
 */
@RestController //@ResponseBody 안 붙혀도 됨
@RequiredArgsConstructor
public class HomeController {
    //[GET : SELECT], [POST : INSERT], [PUT : UPDATE], [DELETE : DELETE]

    private final HomeService homeService;

    @PostMapping(value="/insert")
    public String insert(@RequestBody User user){
        System.out.println("id1: " + user.getId() + ", pw: " + user.getPassword());
        homeService.insert(user);
        return "insert";
    }

    @PostMapping(value="/shot")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        System.out.println(file);
        homeService.uploadImage(file);
        return "shot";
    }

    @PostMapping("/ocr")
    public OcrData ocr() {
        String ocrResult = homeService.ocr();
        OcrData data = new OcrData();
        data.setData(ocrResult);
        // MyData 객체를 JSON 형식으로 자동으로 변환해서 반환합니다.
        return data;
    }
}