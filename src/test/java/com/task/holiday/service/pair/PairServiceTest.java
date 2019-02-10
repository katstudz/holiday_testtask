package com.task.holiday.service.pair;

import com.task.holiday.model.*;
import com.task.holiday.model.external.api.Holiday;
import com.task.holiday.model.external.api.HolidaysList;
import com.task.holiday.service.external.IHolidayApiExternalService;
import com.task.holiday.tools.ICheckValidation;
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
import java.time.LocalDate;
import java.util.ArrayList;
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

    @MockBean
    private ICheckValidation checkValidation;

    private String plHolidayName = "Sylwester";
    private String holidayDate = "1995-12-23";
    private LocalDate date;
    private String pattern = "yyyy-MM-dd";

    @Before
    public void setup() throws ParseException {
        Holiday plHoliday = new Holiday(plHolidayName, holidayDate, "observed", true);
        List<Holiday> plList = new ArrayList<>();
        plList.add(plHoliday);
        HolidaysList plHolidaysList = new HolidaysList(plList);
        when(externalService.getHolidaysList(any())).thenReturn(Optional.of(plHolidaysList));

        SimpleDateFormat format = new SimpleDateFormat(pattern);

        date = LocalDate.of(1995, 12, 23);
        when(checkValidation.check(any())).thenReturn(true);
    }

    @Test
    public void getPairFromResponse_shouldHaveCorrectVariable() {
        CountryPairRequest request = new CountryPairRequest("PL", "PL", date );
        Optional<HolidayPairResponse> optionalHolidayPairResponse = pairService.getPairResponse(request);

        if(optionalHolidayPairResponse.isPresent()){
            HolidayPairResponse pairResponse =optionalHolidayPairResponse.get();
            Assert.assertEquals(holidayDate, pairResponse.getDate());
            Assert.assertEquals(plHolidayName, pairResponse.getName1());
            Assert.assertEquals(plHolidayName, pairResponse.getName2());
        }
        else
            Assert.fail("get HolidayPairResponse empty");

    }

}