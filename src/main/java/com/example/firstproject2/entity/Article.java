package com.example.firstproject2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue // 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
