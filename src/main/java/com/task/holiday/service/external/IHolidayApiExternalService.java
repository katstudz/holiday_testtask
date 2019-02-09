package com.task.holiday.service.external;

import com.task.holiday.model.HolidayApiQueryParams;
import com.task.holiday.model.HolidaysList;

import java.util.Optional;

public interface IHolidayApiExternalService {
    Optional<HolidaysList> getHolidaysList(HolidayApiQueryParams params);
}
