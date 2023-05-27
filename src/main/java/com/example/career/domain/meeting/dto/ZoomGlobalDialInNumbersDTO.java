package com.example.career.domain.meeting.dto;

import lombok.Data;

import java.io.Serializable;

//Zoom 글로벌 전화 접속 번호 DTO
@Data
public class ZoomGlobalDialInNumbersDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String country;

    private String country_name;

    private String city;

    private String number;

    private String type;
}