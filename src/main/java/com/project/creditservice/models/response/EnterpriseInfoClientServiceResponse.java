package com.project.creditservice.models.response;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EnterpriseInfoClientServiceResponse {
    private String name;
    private String ruc;
    private AddressClientServiceResponse address; // current direction
    private ArrayList<PersonInfoClientServiceResponse> holders;

}
