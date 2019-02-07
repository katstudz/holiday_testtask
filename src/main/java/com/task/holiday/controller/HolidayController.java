package com.task.holiday.controller;

import com.task.holiday.model.HolidayApiQueryParams;
import com.task.holiday.service.HolidayApiExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

//    @Autowired
//    private IHolidayPairService pairService;

    @Autowired
    private HolidayApiExternalService holidayApiExternalService;

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
//    public String getHolidayPair(@RequestBody @Valid CountryPairRequest countryPairRequest){
//        Optional<HolidayPairResponse> holidayPairResponse = pairService.getHolidayPair(countryPairRequest);
//        return holidayPairResponse.toString();
//    }
}