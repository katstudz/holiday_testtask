package com.task.holiday.service;

import com.task.holiday.model.*;
import com.task.holiday.service.external.IHolidayApiExternalService;
import com.task.holiday.service.pair.PairService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)

public class PairServiceTest {
    @TestConfiguration
    static class PairServiceTestContextConfiguration {

        @Bean
        public PairService pairService() {
            return new PairService();
        }
    }

    @Autowired
    private PairService pairService;

    @MockBean
    private IHolidayApiExternalService externalService;

    private String plHolidayName = "Sylwester";
    private String holidayDate = "1994-12-30";
    private Date date;
    private String pattern = "yyyy-MM-dd";

    @Before
    public void setup() throws ParseException {
        Holiday plHoliday = new Holiday(plHolidayName, holidayDate, "observed", true);
        List<Holiday> plList = new ArrayList<>();
        plList.add(plHoliday);
        HolidaysList plHolidaysList = new HolidaysList(plList);
        when(externalService.getHolidaysList(any())).thenReturn(Optional.of(plHolidaysList));


        SimpleDateFormat format = new SimpleDateFormat(pattern);
        date = format.parse(holidayDate);
    }

    @Test
    public void getPairFromResponse_shouldHaveCorrectVariable() {
        HolidayPairResponse p;

        CountryPairRequest request = new CountryPairRequest("PL", "PL", date );
        HolidayPairResponse pairResponse = pairService.getPairResponse(request);

        Assert.assertEquals(holidayDate, pairResponse.getDate());
        Assert.assertEquals(plHolidayName, pairResponse.getName1());
        Assert.assertEquals(plHolidayName, pairResponse.getName2());
    }

}