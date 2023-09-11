package com.example.career.domain.community.Repository;

import com.example.career.domain.community.Entity.Comment;
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
    public void updateContentByuserId(@Param("id") Long id, @Param("content") String content, @Param("userId") Long userId, @Param("updatedAt") LocalDateTime updatedAt);

    @Transactional
    public void deleteByUserIdAndId(Long UserId, Long id);
}
