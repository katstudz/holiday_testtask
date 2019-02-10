package com.task.holiday.model.external.api;

public enum APIParameter {
    API_KEY("key"),
    COUNTRY("country"),
    YEAR("year"),
    MONTH("month"),
    DAY("day");

    private String param;

    APIParameter(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return param;
    }
}