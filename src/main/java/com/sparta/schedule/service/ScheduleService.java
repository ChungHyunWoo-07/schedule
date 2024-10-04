package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequesDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component //빈 객체
public class ScheduleService {

    private static final Map<Long, Schedule> scheduleList = new HashMap<>();

    public static ScheduleResponseDto creatSchedule(ScheduleRequesDto requestDto) {
        //RequesDto -> Entity
        Schedule schedule = new Schedule(requestDto);

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

    public List<ScheduleResponseDto> getSchedule() {
        //Map To List
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        return responseList;
    }

    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequesDto requesDto) {
        //해당 스케줄이 DB에 존재하는지 확인
        if (scheduleList.containsKey(id)) {
            //해당 스케줄 가져오기
            Schedule schedule = scheduleList.get(id);
            // 스케줄 수정
            schedule.update(requesDto);
            return schedule.getId();
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }

    }
    public Long deleteSchedule(@PathVariable Long id) {
        //해당 스케줄이 DB에 존재하는지 확인
        if (scheduleList.containsKey(id)) {
            // 해당 스케줄 삭제하기
            scheduleList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }


}
