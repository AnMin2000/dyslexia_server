package com.codingrecipe.project01.repository;

import com.codingrecipe.project01.dto.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class HomeRepository { // 레포지토리 구성은 여러가지가 있음
    private final SqlSessionTemplate sql; //자바에서 지원하는 클래스
    public void insert(User user, Album album){

//        System.out.println(album.getAlbumID() + "**" + album.getUserID());
        sql.insert("Board.signUp", user);
        sql.insert("Board.addAlbumId", album);
    }

    public int login(User user){

        int count = sql.selectOne("Board.login", user);

        return count;

    }
    public void search(User user) {
        // MyBatis select 메서드를 호출하여 쿼리를 실행
        String password = sql.selectOne("Board.search", user);

        // 결과를 User 객체에 설정
        user.setPassword(password);
        System.out.println(user.getPassword());
    }

    public void uploadImage(Picture picture)
    {
        sql.insert("Board.uploadImage", picture);
    }

    public void ocr(Text text){

        sql.insert("Board.text", text);
    }

    public void summarizeText(SumDB sumDB) {

        sql.insert("Board.summarize", sumDB);
    }
}
