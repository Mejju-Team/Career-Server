package com.example.career.domain.consult.Controller;

import com.example.career.domain.consult.Dto.ConsultRespDto;
import com.example.career.domain.consult.Entity.Consult;
import com.example.career.domain.consult.Repository.ConsultRepository;
import com.example.career.domain.consult.Service.ConsultService;
import com.example.career.global.valid.ValidCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConsultController {
    private final ConsultRepository consultRepository;
    private final ConsultService consultService;
//    private final ValidCheck validCheck;
    @GetMapping ("test/get-consult")
    public List<Consult> consultTest(){
        return consultRepository.findAll();
    }
    @GetMapping ("consultation/list")
    public ValidCheck consultUpcoming(@RequestParam int status){
        return new ValidCheck(consultService.getList(10L, status));
    }
}
