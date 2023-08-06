package com.example.career.domain.user.Dto;

import com.example.career.domain.user.Entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private Long idx;
    private String name;

    public Tag toTagEntity(Long id) {
        return Tag.builder()
                .tutor_id(id)
                .idx(idx)
                .name(name)
                .build();
    }
}
