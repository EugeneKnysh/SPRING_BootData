package com.example.bootdata.dto;

import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String anons;
    private String fullText;
    private int views;
}
