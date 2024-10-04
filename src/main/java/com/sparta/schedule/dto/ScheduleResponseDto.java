package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String username;
    private String contents;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUsername();
        this.contents = schedule.getContents();
    }


    public ScheduleResponseDto(Long id, String username, String contents) {
        this.id = id;
        this.username = username;
        this.contents = contents;
    }
}

