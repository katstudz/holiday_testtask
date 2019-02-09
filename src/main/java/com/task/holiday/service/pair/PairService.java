package com.task.holiday.service.pair;

import com.task.holiday.model.*;
import com.task.holiday.service.external.IHolidayApiExternalService;
import com.task.holiday.tools.ICheckValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class PairService implements IPairService {
    @Autowired
    private IHolidayApiExternalService holidayApiExternalService;

    @Autowired
    private ICheckValidation validationChecker;

    private HolidayApiQueryParams createHolidayApiQueryParams(String countryCode, Date date){
        return new HolidayApiQueryParams()
                .country(countryCode)
                .year(date.getYear())
                .month(date.getMonth())
                .day(date.getDay());
    }

    @Override
    public Optional<HolidayPairResponse> getPairResponse(CountryPairRequest countryPairRequest) {
        if(validationChecker.check(countryPairRequest)){
            HolidayPairResponse holidayPairResponse = processCountryPairRequest(countryPairRequest);
            return Optional.of(holidayPairResponse);
        }
        return Optional.empty();
    }

    private HolidayPairResponse processCountryPairRequest(CountryPairRequest countryPairRequest) {
        String firstHolidayNameOptional = getHolidayName(countryPairRequest.getFirstCountryCode(),
                countryPairRequest.getDate());
        String secondHolidayNameOptional = getHolidayName(countryPairRequest.getSecondCountryCode(),
                countryPairRequest.getDate());

        String date = dateToString(countryPairRequest.getDate());
        return new HolidayPairResponse(firstHolidayNameOptional,
                secondHolidayNameOptional, date);
    }

    private String dateToString(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
