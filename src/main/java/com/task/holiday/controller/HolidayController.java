package com.task.holiday.controller;

import com.task.holiday.model.CountryPairRequest;
import com.task.holiday.model.HolidayApiQueryParams;
import com.task.holiday.model.HolidayPairResponse;
import com.task.holiday.service.external.HolidayApiExternalService;
import com.task.holiday.service.pair.IPairService;
import com.task.holiday.tools.ResponseFactory;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    @Autowired
    private IPairService pairService;

    @Autowired
    private HolidayApiExternalService holidayApiExternalService;


    @PostMapping("/forCountryPair")
    public ResponseEntity getForCountryPair(@RequestBody @Valid CountryPairRequest countryPairRequest){
        Optional<HolidayPairResponse> optionalHolidayPairResponse =  pairService.getPairResponse(countryPairRequest);
        return ResponseFactory.optionalOk(optionalHolidayPairResponse);
    }

    @PostMapping("/post")
    public String test(){
        HolidayApiQueryParams request  = new HolidayApiQueryParams()
                .country("PL")
                .year(2013)
                .day(1)
                .month(1);
        return holidayApiExternalService.getHolidaysList(request).toString();
    }

//    @PostMapping("/getPairForCountry") //todo add error's info
//    public String getHolidayPair(@RequestBody @CountryRequestValid CountryPairRequest countryPairRequest){
//        Optional<HolidayPairResponse> holidayPairResponse = pairService.getHolidayPair(countryPairRequest);
//        return holidayPairResponse.toString();
//    }
}