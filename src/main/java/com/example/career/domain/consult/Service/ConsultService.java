package com.example.career.domain.consult.Service;

import com.example.career.domain.consult.Dto.ConsultEachRespDto;
import com.example.career.domain.consult.Dto.ConsultRespDto;
import com.example.career.domain.consult.Dto.ConsultYesorNoReqDto;

import java.util.List;

public interface ConsultService {
    public List<ConsultRespDto> getList(Long tutorId, int status);
    public ConsultEachRespDto requestConsult(ConsultYesorNoReqDto consultYesorNoReqDto, int status);
}
