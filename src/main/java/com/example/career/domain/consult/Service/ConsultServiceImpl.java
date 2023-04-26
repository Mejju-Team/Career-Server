package com.example.career.domain.consult.Service;

import com.example.career.domain.consult.Dto.ConsultRespDto;
import com.example.career.domain.consult.Entity.Consult;
import com.example.career.domain.consult.Repository.ConsultRepository;
import com.example.career.domain.consult.Repository.QueryRepository;
import com.example.career.domain.user.Repository.StudentDetailRepository;
import com.example.career.domain.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService{
// 0: 상담 수락 전, 1: 상담 전, 2: 상담 종료, 3:상담 거절
    private final ConsultRepository consultRepository;
    private final UserRepository userRepository;
    private final QueryRepository queryRepository;
    @Override
    public List<ConsultRespDto> getList(Long tutorId, int status) {
        List<Consult> consultList = consultRepository.findAllByTutorIdAndStatus(tutorId, status);

        List<ConsultRespDto> consultRespDtoList = new ArrayList<>();

        for (Consult consult : consultList) {
            ConsultRespDto consultRespDto = mapToConsultRespDto(consult);
            consultRespDtoList.add(consultRespDto);
        }

        return consultRespDtoList;
    }

    private ConsultRespDto mapToConsultRespDto(Consult consult) {
        ConsultRespDto consultRespDto = new ConsultRespDto();
        consultRespDto.setConsultId(consult.getId());
        consultRespDto.setMajor(consult.getMajor());
        consultRespDto.setZoomLink(consult.getZoomLink());
        consultRespDto.setQuery(queryRepository.findByConsultId(consult.getId()));
        consultRespDto.setMenteeRespDto(userRepository.findById(consult.getStuId()).get().toMenteeRespDto());
        return consultRespDto;
    }

}
