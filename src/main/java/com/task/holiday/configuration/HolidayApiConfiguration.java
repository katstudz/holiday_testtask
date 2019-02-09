package com.task.holiday.configuration;

import com.task.holiday.model.HolidayApiConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HolidayApiConfiguration
{
    @Bean
    public HolidayApiConfig holidayApiConfig() {
        return new HolidayApiConfig("7f14e06c-c6ca-4a0b-bbd4-b47fd70aa8f4",
                "https://holidayapi.com/v1/holidays?");
    }
}
