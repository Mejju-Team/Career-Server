package com.example.career.domain.meeting.dto;

import lombok.Data;

import java.io.Serializable;

// Zoom 회의 반복 DTO
@Data
public class ZoomMeetingRecurrenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer type;

    private Integer repeat_interval;

    private String weekly_days;

    private Integer monthly_day;

    private Integer monthly_week;

    private Integer monthly_week_day;

    private Integer end_times;

    private String end_date_time;
}
