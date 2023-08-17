package com.codingrecipe.project01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
@Getter
@Setter
public class UserDTO { //DB에서 데이터를 받아오는 패키지
    private String id; // _id로 지정
    private String password;
}