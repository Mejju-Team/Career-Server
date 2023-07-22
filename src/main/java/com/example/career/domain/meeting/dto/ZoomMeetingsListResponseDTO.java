package com.example.career.domain.meeting.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data

public class ZoomMeetingsListResponseDTO implements Serializable {

    private static final long serialVersionUID = -218290644483495371L;

    private Integer page_size;

    private Integer page_number;

    private Integer page_count;

    public Integer total_records;

    public String next_page_token;

    public List<ZoomMeetingObjectDTO> meetings;
}
