package com.example.career.domain.community.Repository;

import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("UPDATE Comment c SET c.content = :content, c.updatedAt = :updatedAt WHERE c.id = :id AND c.userId = :userId")
    @Transactional
    public void updateContentByuserIdAndId(@Param("id") Long id, @Param("content") String content, @Param("userId") Long userId, @Param("updatedAt") LocalDateTime updatedAt);

    @Transactional
    public void deleteByUserIdAndId(Long UserId, Long id);

    @Query("SELECT DISTINCT a FROM Article a INNER JOIN Comment c ON a.id = c.articleId AND c.userId = :userId UNION SELECT DISTINCT a FROM Article a INNER JOIN Recomment r ON a.id = r.articleId AND r.userId = :userId")
    Page<Article> findArticlesByUserId(Long userId, Pageable pageable);
}
