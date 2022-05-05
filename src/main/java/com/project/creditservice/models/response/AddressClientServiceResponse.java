package com.project.creditservice.models.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddressClientServiceResponse {
    private String street;
    private String city;
    private String country;
    private String extendedAddress;
}
