package com.codingrecipe.project01.service;

import com.codingrecipe.project01.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<byte[]> getImages(String userID) {
        List<byte[]> imageList = new ArrayList<>();

        // Repository에서 해당 userID에 해당하는 이미지 파일 경로 리스트를 가져옴
        List<String> imagePaths = null;
        imagePaths.add("C:\\Users\\user\\Desktop\\Album\\JPEG_20230911.jpg");
        imagePaths.add("C:\\Users\\user\\Desktop\\Album\\JPEG_20230911.jpg");
        imagePaths.add("C:\\Users\\user\\Desktop\\Album\\JPEG_20230911.jpg");
//        List<String> imagePaths = imageRepository.findImagePathsByUserID(userID);

        // 이미지 파일 경로를 사용하여 이미지를 읽어와서 바이트 배열로 변환
        for (String imagePath : imagePaths) {
            try {
                byte[] imageBytes = readImageFile(imagePath);
                if (imageBytes != null) {
                    imageList.add(imageBytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imageList;
    }

    // 파일을 읽어와 바이트 배열로 변환하는 메서드
    private byte[] readImageFile(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            return Files.readAllBytes(imageFile.toPath());
        }
        return null;
    }
}
