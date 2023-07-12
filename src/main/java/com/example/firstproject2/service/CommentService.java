package com.example.firstproject2.service;

import com.example.firstproject2.dto.CommentDto;
import com.example.firstproject2.entity.Article;
import com.example.firstproject2.entity.Comment;
import com.example.firstproject2.repository.ArticleRepository;
import com.example.firstproject2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 조회: 댓글 목록을 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 변환: 엔티티 -> Dto
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }

        반환:
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(long articleId, CommentDto dto) {
        // 게시글 조회 및 예외처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글 없음"));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTo로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }
}
