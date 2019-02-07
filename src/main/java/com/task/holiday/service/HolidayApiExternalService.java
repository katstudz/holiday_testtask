package com.task.holiday.service;

import com.task.holiday.model.HolidayApiQueryParams;
import com.task.holiday.model.HolidayDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class HolidayApiExternalService {
    private RestTemplate restTemplate = new RestTemplate();

    private static final String REST_URI
            = "https://holidayapi.com/v1/holidays?";
    private String key = "7f14e06c-c6ca-4a0b-bbd4-b47fd70aa8f4";


    private String createPathParam(HolidayApiQueryParams params){
        String keyParam = "key="+ key +"&";
        return REST_URI + keyParam + params.toPathVariable();
    }

    public Optional<HolidayDataResponse> getJsonEmployee(HolidayApiQueryParams params) {
        String fooResourceUrl = createPathParam(params);
        ResponseEntity<HolidayDataResponse> response =
                restTemplate.getForEntity(fooResourceUrl , HolidayDataResponse.class);

        if(response.getStatusCode() == HttpStatus.OK){
            HolidayDataResponse holidayDataResponse = response.getBody();
            assert holidayDataResponse != null;
            return Optional.of(holidayDataResponse);
        }
        return Optional.empty();
    }
}

