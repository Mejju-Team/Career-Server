package com.example.career.domain.community.Repository;

import com.example.career.domain.community.Entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Article a SET a.title = :title, a.content = :content, a.updatedAt = :updatedAt WHERE a.id = :id AND a.userId = :userId")
    void updateArticleTitleAndContent(@Param("userId") Long userId, @Param("id") Long id, @Param("title") String title, @Param("content") String content,  @Param("updatedAt") LocalDateTime updatedAt);


    @Modifying
    @Transactional
    @Query("UPDATE Article a SET a.heartCnt = a.heartCnt + 1 WHERE a.id = :id")
    void incrementArticleThumbsUp(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Article a SET a.heartCnt = a.heartCnt - 1 WHERE a.id = :id")
    void decrementArticleThumbsUp(@Param("id") Long id);

    @Transactional
    void deleteByIdAndUserId(Long Id, Long userId);
}
