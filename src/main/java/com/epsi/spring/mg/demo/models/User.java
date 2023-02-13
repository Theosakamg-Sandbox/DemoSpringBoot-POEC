package com.epsi.spring.mg.demo.models;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class User {

    private String firstname;
    private String lastname;
    private String civility;
    private LocalDateTime dob;
    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime updatedAt = ZonedDateTime.now();

    public String fullname() {
        return String.format("%s %s", this.firstname, this.lastname.toUpperCase());
    }
}
