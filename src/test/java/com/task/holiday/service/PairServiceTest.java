package com.task.holiday.service;

import com.task.holiday.model.Holiday;
import com.task.holiday.model.HolidayPairResponse;
import com.task.holiday.model.HolidaysList;
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

import static org.junit.Assert.*;
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

    private String holidayName = "holidayName";
    private String holidayDate = "1994-02-12";
    private Date date;

    @Before
    public void setup() throws ParseException {
        Holiday holiday = new Holiday(holidayName, holidayDate, "observed", true);
        List<Holiday> list = new ArrayList<>();
        list.add(holiday);
        HolidaysList holidaysList = new HolidaysList(list);
        when(externalService.getHolidaysList(any())).thenReturn(Optional.of(holidaysList));


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse ( holidayDate);
    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String parsedDate = pairService.dateToString(date);
        Assert.assertEquals(holidayDate, parsedDate);
    }

    @Test
    public void tes3t() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = format.parse ( holidayDate);

        String parsedDate = pairService.dateToString(date);
        Assert.assertEquals(holidayDate, parsedDate);
    }
}