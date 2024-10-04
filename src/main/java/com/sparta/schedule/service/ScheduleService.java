package com.sparta.schedule.service;

import com.sparta.schedule.db.DB;
import com.sparta.schedule.dto.ScheduleRequesDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ScheduleService {

    private static final Map<Long, Schedule> scheduleList = new HashMap<>();
    private final DB db = new DB();

    public void creat(ScheduleRequesDto requestDto) throws SQLException {
        //RequesDto -> Entity
        Schedule schedule = new Schedule(null, requestDto.getContents(), requestDto.getUsername());
        db.create(schedule);

    }

    public List<ScheduleResponseDto> getSchedule() {
        //Map To List
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        return responseList;
    }

    public Long update(@PathVariable Long id, @RequestBody ScheduleRequesDto requesDto) {
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
