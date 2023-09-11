package com.example.career.domain.community.Service;

import com.example.career.domain.community.Dto.CommentDto;
import com.example.career.domain.community.Entity.Comment;
import com.example.career.domain.community.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment addComment(CommentDto commentDto, Long userId) {
        return commentRepository.save(commentDto.toCommentEntity(userId));
    }

    public void updateComment(CommentDto commentDto, Long userId) {
        commentRepository.updateContentByuserId(commentDto.getId(), commentDto.getContent(), userId, LocalDateTime.now());
    }

    @Transactional
    public void deleteCommentByUserIdAndId(Long userId, Long id) {
        commentRepository.deleteByUserIdAndId(userId, id);
    }
}
