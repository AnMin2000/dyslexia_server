package com.codingrecipe.project01.service;

import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;

    public void insert(User user){
        System.out.println("id2: " + user.getId() + ", pw: " + user.getPassword());
        homeRepository.insert(user);
    }

    @Value("${uploadPath}")
    private String uploadPath;
    public String uploadImage(MultipartFile file) {
        try {
            // 이미지를 서버에 저장
            String fileName = file.getOriginalFilename();
            File imageFile = new File(uploadPath + File.separator + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();

            return "이미지가 성공적으로 저장되었습니다.";
        } catch (IOException e) {
            e.printStackTrace();
            return "이미지 저장 중 오류가 발생했습니다.";
        }
    }
}
