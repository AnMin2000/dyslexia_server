package com.codingrecipe.project01.service;

import com.codingrecipe.project01.dto.Album;
import com.codingrecipe.project01.dto.Picture;
import com.codingrecipe.project01.dto.Text;
import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;
    File imageFile;

    public void insert(User user) {

        Album album = new Album();

        album.setAlbumID(user.getId()+"*");
        album.setUserID(user.getId());

        homeRepository.insert(user, album);
    }
    public int login(User user) {

        int count = homeRepository.login(user);

        return count;
    }
    public void search(User user) {

       homeRepository.search(user);

    }

    @Value("C:/Users/user/Desktop/Album/")
    private String uploadPath;

    public void uploadImage(MultipartFile file, String id) {
        try {
            // 이미지를 서버에 저장
            String fileName = file.getOriginalFilename();
            imageFile = new File(uploadPath + File.separator + fileName);

            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();


            // 이미지 파일의 저장 날짜 가져오기
            Date lastModifiedDate = new Date(imageFile.lastModified());

            // SimpleDateFormat을 사용하여 날짜 및 시간 형식 지정
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateFormat.format(lastModifiedDate);

            // 이미지 파일의 저장 날짜를 출력
            System.out.println("이미지 파일의 저장 날짜: " + formattedDateTime);
//            System.out.println("파일 경로 : " + uploadPath+fileName);
            Picture picture = new Picture();

            picture.setPictureID(fileName);
            picture.setRoute(uploadPath+fileName);
            picture.setDate(formattedDateTime);
            picture.setAlbumID(id+"*");

            homeRepository.uploadImage(picture);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String ocr(String id, String fileName) {
        try {
            // 이미지를 File로 저장

            ITesseract tesseract = new Tesseract();

            // Tesseract OCR의 데이터 파일이 위치한 리소스 경로를 설정
            String tessDataPath = "C:/Program Files/Tesseract-OCR/tessdata";
            tesseract.setDatapath(tessDataPath);

            // 한국어 언어 설정
            tesseract.setLanguage("kor");

            String ocrResult = tesseract.doOCR(imageFile);

            // OCR 결과에서 줄 바꿈 및 공백 제거하여 텍스트 연결
            ocrResult = ocrResult.replaceAll("\\n", " ").replaceAll("\\s+", " ");


            String finalResult = ocrResult.trim();// 문자열 앞뒤의 공백 제거


            Date today = new Date();
            Locale currentLocale = new Locale("KOREAN", "KOREA");
            String pattern = "yyyyMMddHHmmss"; //hhmmss로 시간,분,초만 뽑기도 가능
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);

            String time = formatter.format(today);

            Text text = new Text();

            text.setTextID(id + ":" + time);
            text.setText(finalResult);
            text.setDate(time);
            text.setPictureID(fileName); //이거 말고 pictureID로 갖고 와야함

            homeRepository.ocr(text);
            return finalResult;
        } catch (TesseractException e) {
            throw new RuntimeException("OCR processing error", e);
        }
    }

    public String summarizeText(String input) {
        System.out.println(input);
        input += " 라는말 20글자 이내로 요약해줘";

        // API 엑세스 토큰
        String apiKey = "";

        // API 엔드포인트 URL
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        // JSON 요청 본문
        String jsonRequest = "{\n" +
                "    \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": [\n" +
                "        {\n" +
                "            \"role\": \"user\",\n" +
                "            \"content\": \"" + input + "\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // HttpClient 초기화
        HttpClient httpClient = HttpClients.createDefault();

        // HTTP POST 요청 생성
        HttpPost httpPost = new HttpPost(apiUrl);

        // 요청 헤더 설정
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + apiKey);

        String jsonResponse = null;
        try {
            // JSON 요청 본문 설정 및 UTF-8 인코딩 지정
            StringEntity entity = new StringEntity(jsonRequest, "UTF-8");
            httpPost.setEntity(entity);

            // HTTP 요청 실행
            HttpResponse response = httpClient.execute(httpPost);

            // HTTP 응답을 문자열로 변환
            HttpEntity responseEntity = response.getEntity();
            jsonResponse = EntityUtils.toString(responseEntity, "UTF-8");
            //System.out.println(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}