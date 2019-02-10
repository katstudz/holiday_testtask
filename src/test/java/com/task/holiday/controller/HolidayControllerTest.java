package com.task.holiday.controller;

import com.task.holiday.HolidayApplication;
import com.task.holiday.model.CountryPairRequest;
import com.task.holiday.model.HolidayPairResponse;
import com.task.holiday.resttemplate.calls.HolidayControllerCalls;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HolidayApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class HolidayControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private HolidayControllerCalls controllerCalls;

    @Before
    public void setup(){
        controllerCalls.setRestTemplate(restTemplate);
    }

    @Test
    public void checkHolidayForNoHolidayDay_getCorrectResponse(){
        LocalDate date = LocalDate.of(1992, 12, 31);
        CountryPairRequest countryPairRequest = new CountryPairRequest("PL", "DE",
               date);

        HttpEntity entity = new HttpEntity<>(countryPairRequest);

        ResponseEntity responseEntity =  controllerCalls.updateConfig(entity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        HolidayPairResponse response = (HolidayPairResponse) responseEntity.getBody();
        Assert.assertEquals("_",response.getName1());
        Assert.assertEquals("_",response.getName2());
    }

    @Test
    public void checkHolidayForSilvester_getCorrectResponse(){
        LocalDate date = LocalDate.of(1992, 1, 1);
        CountryPairRequest countryPairRequest = new CountryPairRequest("PL", "DE",
                date);

        HttpEntity entity = new HttpEntity<>(countryPairRequest);

        ResponseEntity responseEntity =  controllerCalls.updateConfig(entity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        HolidayPairResponse response = (HolidayPairResponse) responseEntity.getBody();
        Assert.assertEquals("Nowy Rok",response.getName1());
        Assert.assertEquals("Neujahrstag",response.getName2());
    }

    @Test
    public void checkHolidayForInCorrectCountryCode_getBadRequest(){
        LocalDate date = LocalDate.of(1992, 1, 1);
        CountryPairRequest countryPairRequest = new CountryPairRequest("K", "DE",
                date);

        HttpEntity entity = new HttpEntity<>(countryPairRequest);

        ResponseEntity responseEntity =  controllerCalls.updateConfig(entity);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}