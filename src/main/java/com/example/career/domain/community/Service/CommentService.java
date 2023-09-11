package com.example.career.domain.community.Service;

import com.example.career.domain.community.Dto.CommentDto;
import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Entity.Comment;
import com.example.career.domain.community.Repository.CommentRepository;
import com.example.career.domain.community.Repository.RecommentRepository;
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
public class CommentService {
    private final CommentRepository commentRepository;
    public List<Article> allCommentedArticles(Long userId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Article> result = commentRepository.findArticlesByUserId(userId, pageable);
        return result.getContent();
    }

    public Comment addComment(CommentDto commentDto, Long userId) {
        return commentRepository.save(commentDto.toCommentEntity(userId));
    }

    public void updateComment(CommentDto commentDto, Long userId) {
        commentRepository.updateContentByuserIdAndId(commentDto.getId(), commentDto.getContent(), userId, LocalDateTime.now());
    }

    @Transactional
    public void deleteCommentByUserIdAndId(Long userId, Long id) {
        commentRepository.deleteByUserIdAndId(userId, id);
    }
}
