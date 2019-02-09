package com.task.holiday.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HolidayApiConfig {
    private String key;
    private String restUri;
}
