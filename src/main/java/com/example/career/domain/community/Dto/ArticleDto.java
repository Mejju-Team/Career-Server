package com.example.career.domain.community.Dto;

import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.user.Entity.Career;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private Long categoryId;
    private String title;
    private String content;

    public Article toArticleEntity(Long userId) {
        return Article.builder()
                .userId(userId)
                .categoryId(categoryId)
                .title(title)
                .content(content)
                .build();

    }
}
