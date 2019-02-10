package com.task.holiday.controller;

import com.task.holiday.model.CountryPairRequest;
import com.task.holiday.model.HolidayPairResponse;
import com.task.holiday.service.pair.IPairService;
import com.task.holiday.tools.ResponseFactory;
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

    @PostMapping("/forCountryPair")
    public ResponseEntity getForCountryPair(@RequestBody @Valid CountryPairRequest countryPairRequest){
        Optional<HolidayPairResponse> optionalHolidayPairResponse =  pairService.getPairResponse(countryPairRequest);
        return ResponseFactory.optionalOk(optionalHolidayPairResponse);
    }
}