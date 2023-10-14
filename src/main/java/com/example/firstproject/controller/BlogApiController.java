package com.example.firstproject.controller;

import com.example.firstproject.domain.Article;
import com.example.firstproject.dto.AddArticleRequest;
import com.example.firstproject.dto.ArticleResponse;
import com.example.firstproject.service.BlogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    // RequestBody 애너테이션은 HTTP를 요청할 때 응답에 해당하는 값을 AddArticleRequest에 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으면 저장된 블로그 글 정보를 응답 객체에 담아 전송
        // ResponseEntity.status().body()는 응답코드 201
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        // 스트림은 자바 8의 기능으로 여러 데이터가 모여있는 컬렉션을 간편하게 처리하기 위한 기능
        // Stream은 컬렉션(Collection)과 함께 사용할 수 있으며 Map을 List로 변환하는데 도움이 될 수 있다.
        // 응답용 객체인 ArticleResponse로 파싱해 body에 담아 클라이언트에게 전송
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }






}
