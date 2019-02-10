package com.task.holiday.service.external;

import com.task.holiday.model.external.api.HolidayApiQueryParams;
import com.task.holiday.model.external.api.HolidaysList;

import java.util.Optional;

public interface IHolidayApiExternalService {
    Optional<HolidaysList> getHolidaysList(HolidayApiQueryParams params);
}
