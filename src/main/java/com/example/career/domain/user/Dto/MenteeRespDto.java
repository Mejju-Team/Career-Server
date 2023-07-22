package com.example.career.domain.user.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenteeRespDto {
    private Long stuId;
    private String nickname;
    private boolean gender;
    private String birth;
    private String profileImg;
    private String stuUrl;
}
