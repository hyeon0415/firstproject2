package com.example.firstproject2.api;

import com.example.firstproject2.dto.ArticleForm;
import com.example.firstproject2.entity.Article;
import com.example.firstproject2.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        // 1: 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2: 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3: 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4: 업데이트 및 정상 응답
        target.patch(article); // body에 일부 데이터를 입력 안할시 그 데이터가 그냥 null이 됨, 기존 데이터 유지 위해
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 대상 찾기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
