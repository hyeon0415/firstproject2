package com.example.firstproject2.repository;

import com.example.firstproject2.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
