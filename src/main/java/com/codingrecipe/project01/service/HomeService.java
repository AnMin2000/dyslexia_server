package com.codingrecipe.project01.service;

import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.io.File;
import java.io.FileNotFoundException;
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

    @Value("C:/Users/user/Desktop/Album/")
    private String uploadPath;
    public void uploadImage(MultipartFile file) {
        try {
            // 이미지를 서버에 저장
            String fileName = file.getOriginalFilename();
            File imageFile = new File(uploadPath + File.separator + fileName);

            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
