package com.task.holiday.tools;

import com.task.holiday.model.CountryPairRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
public class CountryRequestValidationCheckerTest {

    private CountryRequestValidationChecker validationChecker;

    @Before
    public void setup(){
        validationChecker = new CountryRequestValidationChecker();
    }

    @Test
    public void checkCorrectCountryPairRequest_returnValid(){
        CountryPairRequest request = new CountryPairRequest(
                "PL", "DE", new Date("1994-12-23"));
        boolean isValid = validationChecker.check(request);
        Assert.assertTrue(isValid);
    }

    @Test
    public void checkAllNameFirstCountryPairRequest_returnValid(){
        CountryPairRequest request = new CountryPairRequest(
                "POLONIA", "DE", new Date("1994-12-23"));
        boolean isValid = validationChecker.check(request);
        Assert.assertFalse(isValid);
    }

    @Test
    public void checkTooShortSecondCountryPairRequest_returnValid(){
        CountryPairRequest request = new CountryPairRequest(
                "PL", "D", new Date("1994-12-23"));
        boolean isValid = validationChecker.check(request);
        Assert.assertFalse(isValid);
    }

}