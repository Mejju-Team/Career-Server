package com.example.career.domain.user.Dto;

import com.example.career.domain.user.Entity.Career;
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

    public Career toCareerEntity(Long id) {
        return Career.builder()
                .tutor_id(id)
                .idx(idx)
                .careerType(careerType)
                .careerName(careerName)
                .startDate(startDate)
                .endDate(endDate)
                .state(state)
                .content(content)
                .build();
    }
}
