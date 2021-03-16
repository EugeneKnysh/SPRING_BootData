package com.example.bootdata.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String anons;
    private String fullText;
    private int views;

    public Post() {
    }

    public Post(String title, String anons, String fullText) {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }
}
