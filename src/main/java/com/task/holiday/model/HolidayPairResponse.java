package com.task.holiday.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class HolidayPairResponse {
    private String name1;
    private String name2;
    private String date;
}
