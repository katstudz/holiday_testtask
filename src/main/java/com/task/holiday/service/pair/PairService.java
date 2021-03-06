package com.task.holiday.service.pair;

import com.task.holiday.model.*;
import com.task.holiday.model.external.api.Holiday;
import com.task.holiday.model.external.api.HolidayApiQueryParams;
import com.task.holiday.model.external.api.HolidaysList;
import com.task.holiday.service.external.IHolidayApiExternalService;
import com.task.holiday.tools.ICheckValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PairService implements IPairService {
    @Autowired
    private IHolidayApiExternalService holidayApiExternalService;

    @Autowired
    private ICheckValidation validationChecker;

    @Override
    public Optional<HolidayPairResponse> getPairResponse(CountryPairRequest countryPairRequest) {
        if(validationChecker.check(countryPairRequest)){
            HolidayPairResponse holidayPairResponse = processCountryPairRequest(countryPairRequest);
            return Optional.of(holidayPairResponse);
        }
        return Optional.empty();
    }

    private HolidayApiQueryParams createHolidayApiQueryParams(String countryCode, LocalDate date){
        return new HolidayApiQueryParams()
                .country(countryCode)
                .year(date.getYear())
                .month(date.getMonthValue())
                .day(date.getDayOfMonth());
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

    private String dateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    private String getHolidayName(String countryCode, LocalDate date ){
        Optional<String> optionalHolidayName = getNameFromFirstHolidayListElement(countryCode, date);
        return optionalHolidayName.orElse("_");
    }

    private Optional<String> getNameFromFirstHolidayListElement(String countryCode, LocalDate date) {
        HolidayApiQueryParams apiQueryParams = createHolidayApiQueryParams(countryCode, date);
        Optional<HolidaysList> optionalHolidaysList = holidayApiExternalService.getHolidaysList(apiQueryParams);
        if(optionalHolidaysList.isPresent()){
            HolidaysList holidaysList = optionalHolidaysList.get();
            return tryGetFirstHolidayFromList(holidaysList);
        }
        return Optional.empty();
    }

    private Optional<String> tryGetFirstHolidayFromList(HolidaysList holidaysList) {
        if(holidaysList.getHolidays().size() > 0){
            Holiday holiday = holidaysList.getHolidays().get(0);
            return Optional.of(holiday.getName());
        }
        return Optional.of("_");
    }
}
