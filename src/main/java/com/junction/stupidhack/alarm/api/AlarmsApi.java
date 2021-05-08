package com.junction.stupidhack.alarm.api;

import com.junction.stupidhack.alarm.dto.AlarmDto;
import com.junction.stupidhack.alarm.dto.AlarmListDto;
import com.junction.stupidhack.alarm.service.AlarmsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alarms")
public class AlarmsApi {
    private final AlarmsService alarmsService;

    public AlarmsApi(AlarmsService alarmsService) {
        this.alarmsService = alarmsService;
    }

    @GetMapping("/owned")
    public AlarmListDto getOwnedAlarms() {
        return new AlarmListDto(alarmsService.getOwnedAlarms());
    }

    @GetMapping("/next")
    public AlarmListDto getNextAlarms(){
        return new AlarmListDto(alarmsService.getNextAlarms());
    }


    @GetMapping("/passed")
    public AlarmListDto getPassedAlarms(){
        return new AlarmListDto(alarmsService.getPassedAlarms());
    }

    @PostMapping()
    public void createAlarm(@RequestBody AlarmDto alarmDto){
        alarmsService.createAlarm(alarmDto);
    }
}
