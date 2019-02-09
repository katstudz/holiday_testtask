package com.task.holiday.service.pair;

import com.task.holiday.model.CountryPairRequest;
import com.task.holiday.model.HolidayPairResponse;

import java.util.Optional;

public interface IPairService{
    Optional<HolidayPairResponse> getPairResponse(CountryPairRequest countryPairRequest);
}
