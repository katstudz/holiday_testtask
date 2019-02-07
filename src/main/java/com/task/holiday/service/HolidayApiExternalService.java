package com.task.holiday.service;

import com.task.holiday.model.HolidayApiRequest;
import com.task.holiday.model.HolidayDataResponse;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Service
public class HolidayApiExternalService {
    private static final String REST_URI
            = "https://holidayapi.com/v1/holidays";
    private String key = "key=7f14e06c-c6ca-4a0b-bbd4-b47fd70aa8f40";

    private Client client = ClientBuilder.newClient();

    public HolidayDataResponse getJsonEmployee(HolidayApiRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl ="https://holidayapi.com/v1/holidays?key=7f14e06c-c6ca-4a0b-bbd4-b47fd70aa8f4&country" +
                "=US&year=2018&month=02&day=07";
        ResponseEntity<HolidayDataResponse> response =
                restTemplate.getForEntity(fooResourceUrl , HolidayDataResponse.class);

        return response.getBody();
    }
}

