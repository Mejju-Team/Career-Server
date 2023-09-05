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
public class LastUpcomingConsult {
    private Long consultId;
    private Long reviewId;
    private String contentsUrl;
    private String zoomLink;
    private int status = 0;
    private String reason;

    private Long tutorId;

    private Long stuId;

    private String major;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
