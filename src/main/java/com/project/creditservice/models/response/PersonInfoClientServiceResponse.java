package com.project.creditservice.models.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonInfoClientServiceResponse {
    private String name;
    private String lastname;
    private String documentNumber;
    private String mobileNumber;
    private String nationality;
    private AddressClientServiceResponse address; // current direction
    private String email;
    private Date birthdate;
}