package com.example.career.domain.community.Dto;

import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Entity.Heart;
import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HeartDto {
    private Long typeId;
    private int type;
    public Heart toHeartEntity(Long userId) {
        return Heart.builder()
                .userId(userId)
                .typeId(typeId)
                .type(type)
                .build();

    }
}
