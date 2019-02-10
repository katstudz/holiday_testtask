package com.task.holiday.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HolidayPairResponse {
    private String name1;
    private String name2;
    private String date;
}
