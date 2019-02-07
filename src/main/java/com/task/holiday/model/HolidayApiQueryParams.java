package com.task.holiday.model;


import lombok.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.task.holiday.model.APIParameter.*;

//key=7f14e06c-c6ca-4a0b-bbd4-b47fd70aa8f4&country=PL&year=2016&month=01
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//public class HolidayApiRequestParam {
//    private String country;
//    private String year;
//    private String month;
//    private String day;
//
//    @Override
//    public String toString(){
//        return "&country=" + country +
//               "&year=" + year +
//               "&month=" + month +
//               "&day=" + day;
//    }
//}
//

public class HolidayApiQueryParams {

    private Map<String, Object> params;

    public HolidayApiQueryParams() {
        params = new HashMap<>();
    }

    public HolidayApiQueryParams key(String key) {
        params.put(API_KEY.toString(), key);
        return this;
    }

    public HolidayApiQueryParams country(String country) {
        params.put(COUNTRY.toString(), country);
        return this;
    }

    public HolidayApiQueryParams year(int year) {
        params.put(YEAR.toString(), year);
        return this;
    }

    public HolidayApiQueryParams month(int month) {
        params.put(MONTH.toString(), month);
        return this;
    }

    public HolidayApiQueryParams day(int day) {
        params.put(DAY.toString(), day);
        return this;
    }

    public String toPathVariable() {

        if (params.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = it.next();
            builder.append(e.getKey()).append("=").append(e.getValue());
            if (it.hasNext()) {
                builder.append("&");
            }
        }

        return builder.toString();
    }

}