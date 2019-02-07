package com.task.holiday.service;

import com.task.holiday.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class PairService implements IPairService{
    @Autowired
    private IHolidayApiExternalService holidayApiExternalService;

    HolidayApiQueryParams createHolidayApiQueryParams(String countryCode, Date date){
        return new HolidayApiQueryParams()
                .country(countryCode)
                .year(date.getYear())
                .month(date.getMonth())
                .day(date.getDay());
    }

    @Override
    public HolidayPairResponse getPairResponse(CountryPairRequest countryPairRequest) {
        String firstHolidayNameOptional = getHolidayName(countryPairRequest.getFirstCountryCode(),
                countryPairRequest.getDate());
        String secondHolidayNameOptional = getHolidayName(countryPairRequest.getSecondCountryCode(),
                countryPairRequest.getDate());

        String date = dateToString(countryPairRequest.getDate());
        return new HolidayPairResponse(firstHolidayNameOptional,
                secondHolidayNameOptional, date);
    }

    private String dateToString(Date date){
        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        return df.format(date);
    }

    private String getHolidayName(String countryCode, Date date ){
        Optional<String> optionalHolidayName = getHolidayList(countryCode, date);
        return optionalHolidayName.orElse("_");
    }

    private Optional<String> getHolidayList(String countryCode, Date date) {
        HolidayApiQueryParams apiQueryParams = createHolidayApiQueryParams(countryCode, date);
        Optional<HolidaysList> optionalHolidaysList = holidayApiExternalService.getHolidaysList(apiQueryParams);
        if(optionalHolidaysList.isPresent()){
            HolidaysList holidaysList = optionalHolidaysList.get();
            Holiday holiday = holidaysList.getHolidays().get(0);
            return Optional.of(holiday.getName());
        }
        return Optional.empty();
    }
}
