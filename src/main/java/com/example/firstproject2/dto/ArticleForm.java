package com.example.firstproject2.dto;

import com.example.firstproject2.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 생성자
@ToString
public class ArticleForm {

    private String title;
    private String content;

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
