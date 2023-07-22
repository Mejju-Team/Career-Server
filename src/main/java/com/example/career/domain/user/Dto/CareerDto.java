package com.example.career.domain.user.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerDto {
    private Long idx;
    private String careerType;
    private String careerName;
    private String startDate;
    private String endDate;
    private String state;
    private String content;
}
