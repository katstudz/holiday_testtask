package com.task.holiday.service.external;

import com.task.holiday.HolidayApplication;
import com.task.holiday.model.HolidayApiQueryParams;
import com.task.holiday.model.HolidaysList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HolidayApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class HolidayApiExternalServiceTest {

    @Autowired
    private HolidayApiExternalService externalService;

    @Test
    public void getResponseForNewYear_responseBodyShouldntBeEmpty_ListShouldContainsOneElem(){
        HolidayApiQueryParams queryParams = new HolidayApiQueryParams()
                .country("PL")
                .day(1)
                .month(1)
                .year(1994);

        Optional<HolidaysList> optionalList = externalService.getHolidaysList(queryParams);
        Assert.assertNotEquals(Optional.empty(), optionalList);
        HolidaysList holidaysList = optionalList.get();
        Assert.assertEquals(1, holidaysList.getHolidays().size());
    }

    @Test
    public void getResponseForNumericCodeCountryParams(){
        HolidayApiQueryParams queryParams = new HolidayApiQueryParams()
                .country("49")
                .day(1)
                .month(1)
                .year(1994);

        Optional<HolidaysList> optionalList = externalService.getHolidaysList(queryParams);
        Assert.assertEquals(Optional.empty(), optionalList);
    }

    @Test
    public void getResponseForIncorrectCountryParams(){
        HolidayApiQueryParams queryParams = new HolidayApiQueryParams()
                .country("KOKOKO")
                .day(1)
                .month(1)
                .year(1994);

        Optional<HolidaysList> optionalList = externalService.getHolidaysList(queryParams);
        Assert.assertEquals(Optional.empty(), optionalList);
    }

}