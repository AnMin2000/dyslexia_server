package com.codingrecipe.project01.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Picture {
    private String pictureID, route, date, albumID;
}
