package com.task.holiday.service.pair;

import com.task.holiday.model.CountryPairRequest;
import com.task.holiday.model.HolidayPairResponse;

public interface IPairService{
    HolidayPairResponse getPairResponse(CountryPairRequest countryPairRequest);
}
