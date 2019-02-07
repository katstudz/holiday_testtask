package com.task.holiday.service;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Setter
@Getter
public class Employee {
    private int id;
    private String firstName;

    // standard getters and setters
}
