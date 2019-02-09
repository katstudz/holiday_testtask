package com.task.holiday.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CountryPairRequest {
    @NotNull
    //todo add validation for len
    private String firstCountryCode;
    @NotNull
    private String secondCountryCode;

    @NotNull
    @DateTimeFormat(pattern="YYYY-MM-DD")
    private LocalDate date;
}