package com.codingrecipe.project01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class User { //DB에서 데이터를 받아오는 패키지
    private String name, phone, id, password; // _id로 지정

}