package com.codingrecipe.project01.controller;

import com.codingrecipe.project01.dto.OcrData;
import com.codingrecipe.project01.dto.Picture;
import com.codingrecipe.project01.dto.Summarize;
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

        homeService.insert(user);
        return "insert";
    }

    @PostMapping(value="/login")
    public int login(@RequestBody User user){

       int count =  homeService.login(user);
       return count;
    }

    @PostMapping(value="/search")
    public User search(@RequestBody User user){

         homeService.search(user);
        System.out.println(user.getPassword());
        return user;
    }

    @PostMapping(value="/shot")
    public String uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("id") String id
    ) {
        homeService.uploadImage(file, id);
        return "shot";
    }

    @PostMapping("/ocr")
    public OcrData ocr(@RequestBody String id, @RequestBody String fileName) {
        String ocrResult = homeService.ocr(id,fileName);

        OcrData data = new OcrData();
        data.setData(ocrResult);
        // MyData 객체를 JSON 형식으로 자동으로 변환해서 반환합니다.
        return data;
    }

    @PostMapping("/summarize")
    public Summarize summarizeText(@RequestBody OcrData data)  {

        String result = homeService.summarizeText(data.getData());
        Summarize data2 = new Summarize();
        data2.setData2(result);
        return data2;
    }
}