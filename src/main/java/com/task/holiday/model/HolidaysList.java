package com.task.holiday.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
@Getter
public class HolidaysList {
    private List<Holiday> holidays;
}
