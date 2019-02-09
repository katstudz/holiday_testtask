package com.task.holiday.tools;

import com.neovisionaries.i18n.CountryCode;
import com.task.holiday.model.CountryPairRequest;
import org.springframework.stereotype.Component;

@Component("CountryRequestValidationChecker")
public class CountryRequestValidationChecker implements ICheckValidation {

    @Override
    public boolean check(CountryPairRequest pairRequest) {
        boolean isFirstCountryCorrect = checkIsStringIsCountryCode(pairRequest.getFirstCountryCode());
        boolean isSecondCountryCorrect = checkIsStringIsCountryCode(pairRequest.getSecondCountryCode());
        return isFirstCountryCorrect && isSecondCountryCorrect;
    }

    private boolean checkIsStringIsCountryCode(String potentialCountryCode){
        CountryCode countryCode = CountryCode.getByCode(potentialCountryCode);
        return countryCode != null;
    }
}
