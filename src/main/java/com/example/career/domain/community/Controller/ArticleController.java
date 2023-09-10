package com.example.career.domain.community.Controller;

import com.example.career.domain.community.Dto.ArticleDto;
import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Repository.ArticleRepository;
import com.example.career.global.annotation.Authenticated;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/community/article")
@RequiredArgsConstructor
public class ArticleController {
    // Article 관련 메서드 및 로직

    private final ArticleRepository articleRepository;

    @GetMapping("all")
    public ResponseEntity<List<Article>> allArticles(@RequestParam int page, @RequestParam int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Article> result = articleRepository.findAll(pageable);
        List<Article> entities = result.getContent(); // 실제 데이터 리스트
        return ResponseEntity.ok(entities);
    }

    @Authenticated
    @PostMapping("/add")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Article article = articleRepository.save(articleDto.toArticleEntity(userId));
        return ResponseEntity.ok(article);
    }

    @PostMapping("/modify")
    public ResponseEntity<Object> modifyArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request) {
        articleRepository.updateArticleTitleAndContent(articleDto.getId(), articleDto.getTitle(), articleDto.getContent(), LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    @Authenticated
    @DeleteMapping("delete")
    public ResponseEntity<Object> deleteArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        articleRepository.findByIdAndUserId(articleDto.getId(), userId).orElseThrow(() -> new AuthenticationException("잘못된 JWT 서명입니다.") {});
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> ExceptionHandler(Exception e) {
        log.info("Exception msg = {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}