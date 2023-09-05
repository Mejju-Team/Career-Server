package com.example.career.domain.consult.Controller;

import com.example.career.domain.consult.Dto.ConsultRespDto;
import com.example.career.domain.consult.Dto.ConsultYesorNoReqDto;
import com.example.career.domain.consult.Entity.Consult;
import com.example.career.domain.consult.Repository.ConsultRepository;
import com.example.career.domain.consult.Service.ConsultService;
import com.example.career.global.valid.ValidCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("consultation")
public class ConsultController {
    private final ConsultRepository consultRepository;
    private final ConsultService consultService;
//    private final ValidCheck validCheck;
    @GetMapping ("test/get-consult")
    public List<Consult> consultTest(){
        return consultRepository.findAll();
    }
    @GetMapping ("list")
    public ValidCheck consultUpcoming(@RequestParam int status){
        return new ValidCheck(consultService.getList(10L, status));
    }
    @PatchMapping("request")
    public ValidCheck consultAcception(@RequestParam int status, @RequestBody ConsultYesorNoReqDto consultYesorNoReqDto){
        return new ValidCheck(consultService.requestConsult(consultYesorNoReqDto, status));
    }

    // 멘토 홈페이지 상담내역 메서드
    @GetMapping ("mentor/{id}")
    public ValidCheck mentorHome(@PathVariable("id") Long userId){
        return new ValidCheck(consultService.getMentorHome(userId));
    }
}
