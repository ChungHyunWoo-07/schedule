package com.sparta.schedule.controller;



import com.sparta.schedule.dto.ScheduleRequesDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {  //입력 받는 것


    private final ScheduleService scheduleService;  //객체 생성

    public ScheduleController(ScheduleService scheduleService){ //빈 객체
        this.scheduleService = scheduleService;
    }

    @PostMapping //Schedule 생성하기
    public ScheduleResponseDto creatSchedule(@RequestBody ScheduleRequesDto requesDto) {
        ScheduleService scheduleService = new ScheduleService();

        return ScheduleService.creatSchedule(requesDto);
    }

    @GetMapping  //Schedule 조회하기
    public List<ScheduleResponseDto> getSchedule() {

        return scheduleService.getSchedule();
    }

    @PutMapping("/{id}") //스케줄 업데이트
    //@PathVariable로 업데이트 할 id 받아오고, 수정 할 내용을 @RequestBody로 받아옴
    public void updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequesDto requesDto) {
        scheduleService.updateSchedule(id, requesDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {

        scheduleService.deleteSchedule(id);
    }

}

