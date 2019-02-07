package com.task.holiday.model;


import lombok.*;

//key=7f14e06c-c6ca-4a0b-bbd4-b47fd70aa8f4&country=PL&year=2016&month=01
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HolidayApiRequest {
    private String country;
    private String year;
    private String month;
    private String day;

    @Override
    public String toString(){
        return "&country=" + country +
               "&year=" + year +
               "&month=" + month +
               "&day=" + day;
    }
}
