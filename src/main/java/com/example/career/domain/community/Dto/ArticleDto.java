package com.example.career.domain.community.Dto;

import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.user.Entity.Career;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long categoryId;
    private String title;
    private String content;

    public Article toArticleEntity(Long userId) {
        return Article.builder()
                .userId(userId)
                .categoryId(categoryId)
                .title(title)
                .content(content)
                .updateAt(LocalDateTime.now())
                .build();

    }
}
