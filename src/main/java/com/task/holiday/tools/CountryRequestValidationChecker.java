package com.task.holiday.tools;

import com.task.holiday.model.CountryPairRequest;

public class CountryRequestValidationChecker implements ICheckValidation {

    @Override
    public boolean check(CountryPairRequest pairRequest) {
        return true;
    }
}
