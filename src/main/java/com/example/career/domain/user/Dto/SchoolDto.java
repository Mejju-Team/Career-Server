package com.example.career.domain.user.Dto;

import com.example.career.domain.user.Entity.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDto {
    private Long idx; // 경력 갯수 idx
    private String schoolType; // 고등, 대학
    private String schoolName;
    private String startDate;
    private String endDate;
    private String state; // 졸업, 졸업예정 등

    public School toSchoolEntity(Long id) {
        return School.builder()
                .tutor_id(id)
                .idx(idx)
                .schoolName(schoolName)
                .startDate(startDate)
                .endDate(endDate)
                .state(state)
                .build();
    }
}
