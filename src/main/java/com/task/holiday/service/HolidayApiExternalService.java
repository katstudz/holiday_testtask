package com.task.holiday.service;

import com.task.holiday.model.HolidayApiQueryParams;
import com.task.holiday.model.HolidaysList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.task.holiday.model.APIParameter.API_KEY;

@Service
public class HolidayApiExternalService implements IHolidayApiExternalService{
    private RestTemplate restTemplate = new RestTemplate();

    private static final String REST_URI
            = "https://holidayapi.com/v1/holidays?";
    private String key = "7f14e06c-c6ca-4a0b-bbd4-b47fd70aa8f4";


    private String createPathParam(HolidayApiQueryParams params){
        String keyParam = API_KEY.toString() + "="+ key +"&";
        return REST_URI + keyParam + params.toPathVariable();
    }

    public Optional<HolidaysList> getHolidaysList(HolidayApiQueryParams params) {
        String fooResourceUrl = createPathParam(params);
        ResponseEntity<HolidaysList> response =
                restTemplate.getForEntity(fooResourceUrl , HolidaysList.class);

        if(response.getStatusCode() == HttpStatus.OK){
            HolidaysList holidaysList = response.getBody();
            assert holidaysList != null;
            return Optional.of(holidaysList);
        }
        return Optional.empty();
    }
}

