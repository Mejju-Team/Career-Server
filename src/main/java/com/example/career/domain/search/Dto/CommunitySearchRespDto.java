package com.example.career.domain.search.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunitySearchRespDto {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private String content;
    private int heartCnt;
    private int commentCnt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
