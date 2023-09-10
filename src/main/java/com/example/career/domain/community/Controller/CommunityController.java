package com.example.career.domain.community.Controller;

import com.example.career.domain.community.Dto.ArticleDto;
import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Repository.ArticleRepository;
import com.example.career.domain.user.Service.UserService;
import com.example.career.domain.user.Service.UserServiceImpl;
import com.example.career.global.annotation.Authenticated;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("community")
@RequiredArgsConstructor
public class CommunityController {

    private final ArticleRepository articleRepository;

    @Authenticated
    @PostMapping("add_article")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Article article = articleRepository.save(articleDto.toArticleEntity(userId));
        return ResponseEntity.ok(article);
    }

    @PostMapping("modify_article")
    public ResponseEntity<Object> modifyArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request) {
        articleRepository.updateArticleTitleAndContent(articleDto.getId(), articleDto.getTitle(), articleDto.getContent(), LocalDateTime.now());
        return ResponseEntity.ok().build();
    }
}
