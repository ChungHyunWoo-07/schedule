package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequesDto {
    private String username;
    private String contents;

    public ScheduleRequesDto(String username, String contents) {
        this.username = username;
        this.contents = contents;
    }
}
