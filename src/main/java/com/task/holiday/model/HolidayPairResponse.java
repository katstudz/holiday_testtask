package com.task.holiday.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class HolidayPairResponse {
    private String name1;
    private String name2;
    private String date;
}
