package com.task.holiday.resttemplate.calls;

import com.task.holiday.model.HolidayPairResponse;
import lombok.Setter;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Setter
public class HolidayControllerCalls {

    private TestRestTemplate restTemplate;

    private String path = "/holiday";

    public ResponseEntity updateConfig(HttpEntity entity ){
        return  restTemplate.exchange(path + "/forCountryPair",
                HttpMethod.POST, entity, HolidayPairResponse.class);
    }

}
