package com.example.firstproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 접근 제어자가 protected인 기본 생성자를 별도의 코드 없이 생성
public class Article {
    @Id // id 필드를 기본키로 저장
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // title 이라는 not null 컬럼과 매핑
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // Builder 애너테이션은 롬복에서 지원. 생성자 위에 입력하면 빌더 패턴 방식으로 객체를 생성할 수 있어 편리
    // 빌더 패턴을 사용하면 객체를 유연하고 직관적으로 생성할 수 있기 때문에 개발자들이 애용하는 디자인 패턴
    // 즉, 빌더 패턴을 사용하면 어느 필드에 어떤 값이 들어가는지 명시적으로 파악 할 수 있다.
    @Builder // 빌더 패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
