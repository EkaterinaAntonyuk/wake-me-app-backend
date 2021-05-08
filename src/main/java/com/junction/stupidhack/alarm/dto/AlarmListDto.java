package com.junction.stupidhack.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AlarmListDto {
    private final List<AlarmDto> items;
}
