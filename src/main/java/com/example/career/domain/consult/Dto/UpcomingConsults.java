package com.example.career.domain.consult.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpcomingConsults {
    private Long consultId;
    private String contentsUrl;
    private String zoomLink;
    private int status;
    private String reason;

    private String major;
    // 학생의 질문들
    private QueryRespDto studentRequest;
    // 학생 정보
    private ConsultMenteeRespDto student;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
