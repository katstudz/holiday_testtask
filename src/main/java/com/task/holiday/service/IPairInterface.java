package com.task.holiday.service;

import com.task.holiday.model.CountryPairRequest;
import com.task.holiday.model.HolidayPairResponse;

public interface IPairInterface {
    HolidayPairResponse getPairResponse(CountryPairRequest countryPairRequest);
}
