package com.example.firstproject.repository;

import com.example.firstproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 클래스를 상속받을 때 엔티티 Article과 엔티티 PK 타입 Long을 인수로 넣는다.
public interface BlogRepository extends JpaRepository<Article, Long> {

}
