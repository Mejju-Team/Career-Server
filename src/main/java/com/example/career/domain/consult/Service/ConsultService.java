package com.example.career.domain.consult.Service;

import com.example.career.domain.consult.Dto.ConsultRespDto;

import java.util.List;

public interface ConsultService {
    public List<ConsultRespDto> getList(Long tutorId, int status);
}
