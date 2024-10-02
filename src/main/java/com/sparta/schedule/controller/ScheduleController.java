package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequesDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping("/schedules") //Schedule 생성하기
    public ScheduleResponseDto creatSchedule(@RequestBody ScheduleRequesDto requesDto){
        //RequesDto -> Entity
        Schedule schedule = new Schedule(requesDto);

        //Schedule Max ID Check
        //Map의 키 값인 Long의 가장 큰 값을 가져와서 keySet에 넣음
        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setId(maxId);

        //DB저장
        scheduleList.put(schedule.getId(), schedule); // 키에는 아이디를 벨류에는 위에서 만든 스케줄 객체를 넣음

        //Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    @GetMapping("/schedules")  //Schedule 조회하기
    public List<ScheduleResponseDto> getSchedule(){
        //Map To List
        List<ScheduleResponseDto> responseList = scheduleList.values().stream().map(ScheduleResponseDto::new).toList();
        return responseList;
    }









}
