package com.sparta.schedule.entity;

import com.sparta.schedule.controller.ScheduleController;
import com.sparta.schedule.dto.ScheduleRequesDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;  // 스케줄끼리 구분
    @Getter
    private String username;  // 작성한 사람의 이름
    @Getter
    private String contents;  // 스케줄 내용

    public Schedule(ScheduleRequesDto requesDto) {
        this.username = requesDto.getUsername();
        this.contents = requesDto.getContents();
    }
    public void update(ScheduleRequesDto requesDto) {
        this.username = requesDto.getUsername();
        this.contents = requesDto.getContents();
    }
}