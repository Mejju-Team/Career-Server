package com.example.career.domain.community.Service;

import com.example.career.domain.community.Dto.ArticleDto;
import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Article> result = articleRepository.findAll(pageable);
        return result.getContent();
    }

    public Article addArticle(ArticleDto articleDto, Long userId) {
        Article article = articleRepository.save(articleDto.toArticleEntity(userId));
        return article;
    }

    @Transactional
    public void updateArticle(ArticleDto articleDto, Long userId) {
        articleRepository.updateArticleTitleAndContent(userId, articleDto.getId(), articleDto.getTitle(), articleDto.getContent(), LocalDateTime.now());
    }

    @Transactional
    public void deleteArticleByUserIdAndId(Long id, Long userId) {
        articleRepository.deleteByIdAndUserId(id, userId);
    }

}