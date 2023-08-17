package com.codingrecipe.project01.repository;

import com.codingrecipe.project01.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HomeRepository { // 레포지토리 구성은 여러가지가 있음
    private final SqlSessionTemplate sql; //자바에서 지원하는 클래스
    public void insert(UserDTO user){
        System.out.println("id3: " + user.getId() + ", pw: " + user.getPassword());
        System.out.println("합 : "+ user.toString());
        sql.insert("Board.insert", user);
    }
}
