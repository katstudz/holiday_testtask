package com.task.holiday.model.external.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.task.holiday.model.external.api.Holiday;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class HolidaysList {
    private List<Holiday> holidays;
}
