package com.junction.stupidhack.alarm.service;

import com.junction.stupidhack.alarm.dto.AlarmDto;
import com.junction.stupidhack.alarm.model.Alarm;
import com.junction.stupidhack.alarm.model.User;
import com.junction.stupidhack.alarm.repository.AlarmRepository;
import com.junction.stupidhack.alarm.repository.UserRepository;
import com.junction.stupidhack.alarm.security.AuthHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlarmsService {
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    public AlarmsService(AlarmRepository alarmRepository, UserRepository userRepository) {
        this.alarmRepository = alarmRepository;
        this.userRepository = userRepository;
    }

    public List<AlarmDto> getOwnedAlarms() {
        return alarmRepository.getByCreatorLoginOrderByDateDesc(AuthHolder.get().getUserId())
                .stream().map(alarm ->
                        new AlarmDto(
                                alarm.getId(),
                                alarm.getCreator().getLogin(),
                                alarm.getDestination().getLogin(),
                                alarm.getDate(),
                                alarm.getMessage(),
                                alarm.getLength()
                        )).collect(Collectors.toList());
    }

    public List<AlarmDto> getNextAlarms() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return alarmRepository
                .getByDestinationLoginAndDateAfterAndDateBeforeOrderByDateDesc(AuthHolder.get().getUserId(),
                        new Date(), calendar.getTime())
                .stream().map(alarm ->
                        new AlarmDto(
                                alarm.getId(),
                                alarm.getCreator().getLogin(),
                                alarm.getDestination().getLogin(),
                                alarm.getDate(),
                                alarm.getMessage(),
                                alarm.getLength()
                        )).collect(Collectors.toList());
    }

    public List<AlarmDto> getPassedAlarms() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        return alarmRepository
                .getByDestinationLoginAndDateAfterAndDateBeforeOrderByDateDesc(AuthHolder.get().getUserId(),
                        calendar.getTime(), new Date())
                .stream().map(alarm ->
                        new AlarmDto(
                                alarm.getId(),
                                alarm.getCreator().getLogin(),
                                alarm.getDestination().getLogin(),
                                alarm.getDate(),
                                alarm.getMessage(),
                                alarm.getLength()
                        )).collect(Collectors.toList());
    }

    public void createAlarm(AlarmDto alarmDto) {
        alarmRepository.save(
                new Alarm(
                        getUser(AuthHolder.get().getUserId()),
                        getUser(alarmDto.getDestination()),
                        alarmDto.getDateTime(),
                        alarmDto.getMessage(),
                        alarmDto.getLength()
                )
        );
    }

    private User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Bad user id: " + userId));
    }
}
