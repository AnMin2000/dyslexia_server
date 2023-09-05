package com.codingrecipe.project01.service;

import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import okhttp3.*;
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
    File imageFile;
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
            imageFile = new File(uploadPath + File.separator + fileName);

            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String ocr() {

        ITesseract tesseract = new Tesseract();

        // Tesseract OCR의 데이터 파일이 위치한 리소스 경로를 설정
        String tessDataPath = "C:/Program Files/Tesseract-OCR/tessdata";
        tesseract.setDatapath(tessDataPath);

        // 한국어 언어 설정
        tesseract.setLanguage("kor");

        try {
            String ocrResult = tesseract.doOCR(imageFile);
            System.out.println(ocrResult);
            return ocrResult;
        } catch (TesseractException e) {
            throw new RuntimeException("OCR processing error", e);
        }
    }

    private static final String API_KEY = "";
    private static final String GPT_API_URL = "https://api.openai.com/v1/engines/davinci/completions";


    private final OkHttpClient client = new OkHttpClient();

    public String summarizeText(String inputText) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
       // inputText = "'안녕하세요 오늘은 날씨가 매우 좋아서 집에 오는 길에 빵을 사왔어' 요약해줘";
        //String requestBody = "{\"prompt\":\"" + "안녕하세요" + "\",\"max_tokens\":50}";
        String requestBody = "{\"prompt\":\"" + inputText + "\",\"max_tokens\":100}";
        Request request = new Request.Builder()
                .url(GPT_API_URL)
                .post(RequestBody.create(mediaType, requestBody))
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new IOException("Failed to summarize text: " + response.code());
            }
        }
    }

}
