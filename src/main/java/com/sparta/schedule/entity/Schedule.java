package com.sparta.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Long id;  // 스케줄끼리 구분
    private String username;  // 작성한 사람의 이름
    private String contents;  // 스케줄 내용

}