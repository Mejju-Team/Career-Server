package com.example.career.domain.meeting.dto;

import lombok.Data;

import java.io.Serializable;

//Zoom 회의 통역사 DTO
@Data
public class ZoomInterpreterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public String email;

    public String languages;
}
