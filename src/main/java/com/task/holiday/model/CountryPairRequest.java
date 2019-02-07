package com.task.holiday.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
public class CountryPairRequest {
    @NotNull
    //todo add validation for len
    private String firstCountryCode;
    @NotNull
    private String secondCountryCode;

    @NotNull
    private Date date;
}