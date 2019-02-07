package com.task.holiday.model;


import lombok.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.task.holiday.model.APIParameter.*;

public class HolidayApiQueryParams {

    private Map<String, Object> params;

    public HolidayApiQueryParams() {
        params = new HashMap<>();
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