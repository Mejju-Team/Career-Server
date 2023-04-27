package com.example.career.domain.consult.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryRespDto {
    private Long id;
    private Long consultId;
    private String flow;
    private String query1;
    private String query2;
    private String etc;

}
