package com.example.firstproject2.api;

import com.example.firstproject2.dto.ArticleForm;
import com.example.firstproject2.entity.Article;
import com.example.firstproject2.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 데이터(JSON) 반환
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;
    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
}
