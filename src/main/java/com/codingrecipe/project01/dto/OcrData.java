package com.codingrecipe.project01.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcrData {

    @SerializedName("data")
    private String data;

}
